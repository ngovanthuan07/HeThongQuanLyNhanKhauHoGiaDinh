package com.vanthuandev.doanphanmem.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class DanToc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDT;

    @NotNull
    @Size(max = 50)
    private String tenDT;

    public DanToc(int maDT, String tenDT) {
        this.maDT = maDT;
        this.tenDT = tenDT;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "danToc", fetch = FetchType.LAZY)
    private Collection<NhanKhau> nhanKhaus;
}
