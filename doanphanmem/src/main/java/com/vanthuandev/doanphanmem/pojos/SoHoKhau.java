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
    private int soHK;

    @NotNull
    @Size(max = 100)
    private String noiDangKyThuongTru;

    @NotNull
    @Size(max = 20)
    private String toSo;

    @Size(max = 100)
    private String hoNgoaiHuyenDen;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayNopLuu;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTach;

    private String trangThai;

    private String chinhSach;

    private String chiChu;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soHoKhau", fetch = FetchType.LAZY)
    private Collection<NhanKhauThuongTru> nhanKhauThuongTrus;

}
