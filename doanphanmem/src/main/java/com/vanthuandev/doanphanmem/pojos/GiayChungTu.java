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
public class GiayChungTu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCT;

    @Size(max = 50)
    private String hoTenKhaiSinh;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    private int gioiTinh;

    private int danToc;

    private String quocTich;

    @Size(max = 25)
    private String cmnd;

    private String noiThuongTru;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayChet;

    private String noiChet;

    private String nguyenNhanChet;

    private String noiDangKy;

    private String ghiChu;
}
