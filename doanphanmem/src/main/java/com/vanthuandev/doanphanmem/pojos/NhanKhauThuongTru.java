package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NhanKhauThuongTru {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNKTT;

    private String noiThuongTruTruocDay;

    private String nhanKhauMoiSinh;

    private String nhanKhauNgoaiHuyenDen;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDi;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDen;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    private String noiDen;

    private String lyDo;

    private String chuThich;

    private int trangThai;

    private String noiChuyenDen;

    @Size(max = 100)
    private String tinhTrang;

    // -- quan he nhieu 1
    @ManyToOne(optional = false)
    @JoinColumn(name = "so_hk", referencedColumnName = "soHK")
    private SoHoKhau soHoKhau;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nk", referencedColumnName = "maNK")
    private NhanKhau nhanKhau;

}
