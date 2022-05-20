package com.vanthuandev.doanphanmem.service;

import com.vanthuandev.doanphanmem.pojos.ChiTiet;

import java.util.List;

public interface ChiTietService {

    <S extends ChiTiet> List<S> saveAll(Iterable<S> entities);
}
