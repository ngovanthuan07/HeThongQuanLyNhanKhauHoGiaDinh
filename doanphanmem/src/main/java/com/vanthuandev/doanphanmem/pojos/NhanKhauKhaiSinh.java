package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NhanKhauKhaiSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNhanKhauKhaiSinh;

    @NotNull
    @Size(max = 50)
    private String hoTenCha;

    @NotNull
    @Size(max = 50)
    private String hoTenMe;

    @NotNull
    @Size(max = 50)
    private String hoTenNguoiKhai;


    @NotNull
    @Size(max = 250)
    private String quanHeVoiNguoiDuocKhaiSinh;

    @Size(max = 100)
    private String tinhTrang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nhan_khau", referencedColumnName = "maNhanKhau")
    private NhanKhau nhanKhau;
}
