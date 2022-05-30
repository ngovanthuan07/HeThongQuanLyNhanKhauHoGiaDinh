package com.vanthuandev.doanphanmem.model;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base {
    private Integer soHK;
    private NhanKhau nhanKhau;
}
