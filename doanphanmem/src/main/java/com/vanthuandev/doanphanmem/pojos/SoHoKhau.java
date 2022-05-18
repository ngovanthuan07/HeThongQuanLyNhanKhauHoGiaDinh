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
public class SoHoKhau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maSoHoKhau;

    @NotNull
    @Size(max = 100)
    private String chuHo;

    @Size(max = 100)
    private String hoChinhSach;

    @Size(max = 250)
    private  String diaChiThuongTru;

    @Size(max = 100)
    private String tinhTrang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_tinh_thanh", referencedColumnName = "maTinhThanh")
    private TinhThanh tinhThanh;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soHoKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauTachHo> nhanKhauTachHos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soHoKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauThuongTru> nhanKhauThuongTrus;

}
