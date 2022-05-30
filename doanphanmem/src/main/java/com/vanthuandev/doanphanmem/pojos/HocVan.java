package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class HocVan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHV;

    @NotNull
    @Size(max = 50)
    private String tenHV;

    public HocVan(int maHV, String tenHV) {
        this.maHV = maHV;
        this.tenHV = tenHV;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hocVan", fetch = FetchType.LAZY)
    private Collection<NhanKhau> nhanKhaus;
}
