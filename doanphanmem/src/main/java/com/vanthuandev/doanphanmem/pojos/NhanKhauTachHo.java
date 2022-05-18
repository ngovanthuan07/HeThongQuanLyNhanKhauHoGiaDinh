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
@Table(name = "nhan_khau_tach_ho")
public class NhanKhauTachHo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTachHo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDi;

    @Size(max = 100)
    private String tinhTrang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_so_ho_khau", referencedColumnName = "maSoHoKhau")
    private SoHoKhau soHoKhau;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nhan_khau_thuong_tru", referencedColumnName = "maNhanKhauThuongTru")
    private NhanKhauThuongTru nhanKhauThuongTru;

}
