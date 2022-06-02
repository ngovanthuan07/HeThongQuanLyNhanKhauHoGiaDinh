package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NhanKhau  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNK;


    @NotNull
    @Size(min= 1, max = 50)
    private String hoVaTen;

    @Size(min= 1, max = 50)
    private String tenGoiKhac;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @NotNull
    private int gioiTinh;

    @NotNull
    @Size(max = 50)
    private String cmnd;

    @NotNull
    @Size(max = 50)
    private String quocTich;


    @Size(max = 100)
    private String trinhDoChuyenMon;

    @Size(max = 100)
    private String trinhDoNgoaiNgu;

    @NotNull
    private  String noiSinh;

    @NotNull
    private  String nguyenQuan;

    @NotNull
    private String noiOHienNay;

    private String noiLamViec;

    @Size(max = 50)
    private String bietTiengDanToc;

    @Size(max = 50)
    private String soHoChieu;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDKTT;

    @Column(columnDefinition = "LONGTEXT")
    private String tomTatBanThan;

    @Column(columnDefinition = "LONGTEXT")
    private String tomTatGiaDinh;

    private String tienAn;

    @Size(max = 100)
    private String ngheNghiep;

    private String noiThuongTruTruocDay;

    private String trangThai;

    private String ghiChu;

    private int hoNgoaiHuyenDen;

    private int nhanKhauMoiSinh;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    // ------ Thiet lap: Ton Giao, Hoc Van, Dan Toc, Quan He
    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_dt", referencedColumnName = "maDT")
    private DanToc danToc;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_hv", referencedColumnName = "maHV")
    private HocVan hocVan;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_tg", referencedColumnName = "maTG")
    private TonGiao tonGiao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_qh", referencedColumnName = "maQH")
    private QuanHe quanHe;

    @Transient
    private int _id;

    // ------

    // Quan he mot nhieu
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauThuongTru> nhanKhauThuongTrus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauTamTru> nhanKhauTamTrus;
}
