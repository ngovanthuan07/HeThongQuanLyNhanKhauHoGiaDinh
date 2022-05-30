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
public class QuanHe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maQH;

    @NotNull
    @Size(max = 50)
    private String tenQH;

    public QuanHe(int maQH, String tenQH) {
        this.maQH = maQH;
        this.tenQH = tenQH;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quanHe", fetch = FetchType.LAZY)
    private Collection<NhanKhau> nhanKhaus;


}
