package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class DangKyHoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHS;

    @NotNull
    @Size(max = 100)
    private String hoVaTen;

    private String cmnd;

    private String diaChi;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDangKy;

    private String ghiChu;

    @Column(columnDefinition = "LONGTEXT")
    private String mauDon;

    private int trangThai;




    @ManyToOne(optional = false)
    @JoinColumn(name = "nguoi_tao", referencedColumnName = "maNguoiDung")
    private NguoiDung nguoiTao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_loai_ho_so", referencedColumnName = "maLHS")
    private LoaiHoSo loaiHoSo;

}
