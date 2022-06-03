package com.vanthuandev.doanphanmem.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

    private String publicId;

    private String diaChi;

    private String soDT;

    private int trangThai;

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nguoiTao", fetch = FetchType.LAZY)
    private Collection<DangKyHoSo> dangKyHoSos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vai_tro_nguoi_dung",
            joinColumns = {@JoinColumn(name = "ma_nguoi_dung")},
            inverseJoinColumns = {@JoinColumn(name = "ma_vai_tro")}
    )
    private Collection<VaiTro> vaiTros = new ArrayList<>();

}
