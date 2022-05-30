package com.vanthuandev.doanphanmem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class NhanKhauTamTruPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTT;

    private int maNK;
}
