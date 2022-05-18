package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class QuanHuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maQuanHuyen;

    @NotNull
    @Size(max = 100)
    private String tenQuanHuyen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_tinh_thanh", referencedColumnName = "maTinhThanh")
    private TinhThanh tinhThanh;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quanHuyen", fetch = FetchType.LAZY)
    private Collection<PhuongXa> phuongXas;
}
