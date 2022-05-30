package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import com.vanthuandev.doanphanmem.repository.SoHoKhauRepository;
import com.vanthuandev.doanphanmem.service.SoHoKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class SoHoKhauServiceImpl implements SoHoKhauService {

    @Autowired
    private SoHoKhauRepository soHoKhauRepository;


    @Override
    public <S extends SoHoKhau> S save(S entity) {
        return soHoKhauRepository.save(entity);
    }

    @Override
    public Optional<SoHoKhau> findById(Integer integer) {
        return soHoKhauRepository.findById(integer);
    }
}
