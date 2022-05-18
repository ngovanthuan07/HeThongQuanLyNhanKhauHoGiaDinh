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
public class NhanKhauDaChet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNhanKhauDaChet;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayChet;

    @NotNull
    @Size(max = 250)
    private String noiChet;

    @NotNull
    @Size(max = 250)
    private String lyDo;

    @NotNull
    @Size(max = 100)
    private String tinhTrang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ma_nhan_khau", referencedColumnName = "maNhanKhau")
    private NhanKhau nhanKhau;
}
