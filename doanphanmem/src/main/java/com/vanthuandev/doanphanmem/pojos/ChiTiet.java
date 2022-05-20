package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ChiTiet {
    @Id
    private String maChiTiet;

    @Size(max = 100)
    private String tuNgayThangNam;

    @Size(max = 250)
    @Column(name = "cho_o")
    private String choO;

    @Size(max = 100)
    private String ngheNghieNoiLamViecHienTai;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nhan_khau", referencedColumnName = "maNhanKhau")
    private NhanKhau nhanKhau;
}
