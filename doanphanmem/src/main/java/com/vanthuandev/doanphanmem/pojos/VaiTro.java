package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maVaiTro;

    @Size(max = 100)
    private String tenVaiTro;

    @Size(max = 100)
    private String code;

/*    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vaiTro", fetch = FetchType.LAZY)
    private Collection<NguoiDung> nguoiDungs;*/
}
