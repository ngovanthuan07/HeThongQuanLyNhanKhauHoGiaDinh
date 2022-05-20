package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NhanKhau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNhanKhau;

    @NotNull
    @Size(min= 1, max = 50)
    private String hoVaTen;

    @Size(min= 1, max = 50)
    private String tenGoiKhac;

    @NotNull
    private int gioiTinh;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @NotNull
    private String nguyenQuan;

    @NotNull
    private  String noiSinh;

    @NotNull
    @Size(max = 50)
    private String danToc;

    @NotNull
    @Size(max = 50)
    private String trinhDoVanHoa;

    @NotNull
    @Size(max = 50)
    private String quocTich;

    @Size(max = 50)
    private String ngheNghiep;

    private String noiLamViec;

    @NotNull
    @Size(max = 50)
    private String cmnd;

    private int trangThai;

    @Size(max = 50)
    private String tinhTrang;

    @NotNull
    private String noiThuongTru;

    @NotNull
    private String choOHienNay;



    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauThuongTru> nhanKhauThuongTrus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauTamTru> nhanKhauTamTrus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauKhaiSinh> nhanKhauKhaiSinhs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauDaChet> nhanKhauDaChets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<ChiTiet> chiTiets;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nguoi_dung", referencedColumnName = "maNguoiDung")
    private NguoiDung nguoiDung;


}
