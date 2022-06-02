package com.vanthuandev.doanphanmem.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NhanKhauTamTru {

    @EmbeddedId
    private NhanKhauTamTruPK nhanKhauTamTruPK;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tamTruTuNgay;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tamTruDenNgay;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDangKyTamTru;

    private String noiThuongTru;


    private String noiDangKiTamTru;

    private String loaiTamTru;

    private String lyDo;

    private String ghiChu;

    private String loaiSo;

    private int trangThai;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayGiaHan;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @MapsId("maNK")
    @JoinColumn(name = "mank", referencedColumnName = "maNK")
    private NhanKhau nhanKhau;

}
