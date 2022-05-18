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
    private int maNhanKhauThuongTru;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDen;

    private String quanHeVoiChuHo;

    @Size(max = 100)
    private String tinhTrang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_so_ho_khau", referencedColumnName = "maSoHoKhau")
    private SoHoKhau soHoKhau;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nhan_khau", referencedColumnName = "maNhanKhau")
    private NhanKhau nhanKhau;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhauThuongTru", fetch = FetchType.LAZY)
    private Collection<NhanKhauTachHo> nhanKhauTachHos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanKhauThuongTru", fetch = FetchType.LAZY)
    private Collection<NhanKhauTamVang> nhanKhauTamVangs;

}
