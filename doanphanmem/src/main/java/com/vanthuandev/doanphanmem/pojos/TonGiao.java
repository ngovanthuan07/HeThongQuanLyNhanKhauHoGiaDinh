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
public class TonGiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTG;

    @NotNull
    @Size(max = 50)
    private String tenTG;

    public TonGiao(int maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tonGiao", fetch = FetchType.LAZY)
    private Collection<NhanKhau> nhanKhaus;
}
