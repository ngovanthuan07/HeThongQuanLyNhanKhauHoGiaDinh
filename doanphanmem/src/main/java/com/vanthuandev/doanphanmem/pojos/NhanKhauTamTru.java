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
public class NhanKhauTamTru {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNhanKhauTamTru;

    private String noiTamTru;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDen;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDi;

    @Size(max = 250)
    private String lyDo;

    @Size(max = 250)
    private String quanHeVoiChuHo;

    @Size(max = 50)
    private String cmndChuHo;

    @Size(max = 100)
    private String tinhTrang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_tinh_thanh", referencedColumnName = "maTinhThanh")
    private TinhThanh tinhThanh;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nhan_khau", referencedColumnName = "maNhanKhau")
    private NhanKhau nhanKhau;

}
