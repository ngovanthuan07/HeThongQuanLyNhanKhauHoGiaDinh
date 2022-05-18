package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class PhuongXa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maPhuongXa;

    @NotNull
    @Size(max = 100)
    private String tenPhuongXa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_quan_huyen", referencedColumnName = "maQuanHuyen")
    private QuanHuyen quanHuyen;
}
