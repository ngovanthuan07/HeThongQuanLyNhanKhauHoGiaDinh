package com.vanthuandev.doanphanmem.pojos;

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

    private String noiThuongTru;

    private String noiDangKiTamTru;

    private String lyDo;

    private String ghiChu;

    private String loaiSo;

    private String trangThai;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;


    @ManyToOne(optional = false)
    @MapsId("maNK")
    @JoinColumn(name = "mank", referencedColumnName = "maNK")
    private NhanKhau nhanKhau;

}
