package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class LoaiHoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLHS;

    private String tenLHS;

    public LoaiHoSo(int maLHS, String tenLHS) {
        this.maLHS = maLHS;
        this.tenLHS = tenLHS;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loaiHoSo", fetch = FetchType.LAZY)
    private Collection<DangKyHoSo> dangKyHoSos;
}
