package com.vanthuandev.doanphanmem.pojos;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class NhanKhauTamTruPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTT;

    private int maNK;
}
