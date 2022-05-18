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
public class TinhThanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTinhThanh;

    @NotNull
    @Size(max = 100)
    private String tenTinhThanh;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tinhThanh", fetch = FetchType.LAZY)
    private Collection<QuanHuyen> quanHuyens;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tinhThanh", fetch = FetchType.LAZY)
    private Collection<SoHoKhau> soHoKhaus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tinhThanh", fetch = FetchType.LAZY)
    private Collection<NhanKhauTamTru> nhanKhauTamTrus;
}
