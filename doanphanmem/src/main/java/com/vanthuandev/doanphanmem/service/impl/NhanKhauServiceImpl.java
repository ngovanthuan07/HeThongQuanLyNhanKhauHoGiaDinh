package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.repository.NhanKhauRepository;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NhanKhauServiceImpl implements NhanKhauService {

    @Autowired
    private NhanKhauRepository nhanKhauRepository;

    @Override
    public <S extends NhanKhau> S save(S entity) {
        return nhanKhauRepository.save(entity);
    }


    @Override
    public Optional<NhanKhau> findById(Integer integer) {
        return nhanKhauRepository.findById(integer);
    }


    @Override
    public <S extends NhanKhau> List<S> saveAll(Iterable<S> entities) {
        return nhanKhauRepository.saveAll(entities);
    }
}
