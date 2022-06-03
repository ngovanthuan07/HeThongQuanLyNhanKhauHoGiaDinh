package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class GiayKhaiSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKS;

    @Size(max = 50)
    private String hoTenKhaiSinh;

    private int gioiTinh;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    private String noiSinh;

    private int danToc;

    private String quocTich;

    private String queQuan;
    // Cha
    @Size(max = 50)
    private String hoTenCha;

    private String namSinhCha;

    private int danTocCuaCha;

    private String quocTichCuaCha;

    private String noiThuongTruCuaCha;

    // Me
    @Size(max = 50)
    private String hoTenMe;

    private int danTocCuaMe;

    private String quocTichCuaMe;

    private String namSinhMe;

    private String noiThuongTruCuaMe;
    // khac
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDangKy;

    private String noiDangKyKhaiSinh;

    private String hoTenNguoiDiKhaiSinh;

    private String quanHeVoiNguoiDuocKhaiSinh;
    // khac 2
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    private String lyDo;

    private String ghiChu;


}
