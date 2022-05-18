package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maNguoiDung;

    @Size(max = 50)
    @NotEmpty(message = "Không được để trống")
    private String tenNguoiDung;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @Size(max = 12)
    private String cmnd;

    @NotNull
    @NotEmpty(message = "Không được để trống")
    private String username;

    @NotNull
    @NotEmpty(message = "Không được để trống")
    private String password;

    private String email;

    private String image;

    private int trangThai;
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "ma_vai_tro", referencedColumnName = "maVaiTro")
//    private VaiTro vaiTro;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    private Collection<NhanKhau> nhanKhaus;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vai_tro_nguoi_dung",
            joinColumns = {@JoinColumn(name = "ma_nguoi_dung")},
            inverseJoinColumns = {@JoinColumn(name = "ma_vai_tro")}
    )
    private Collection<VaiTro> vaiTros = new ArrayList<>();

}
