package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import com.vanthuandev.doanphanmem.repository.SoHoKhauRepository;
import com.vanthuandev.doanphanmem.service.SoHoKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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


    @Override
    public Optional<SoHoKhau> findSoHoKhauBySoHK(int soHK, int tt) {
        return soHoKhauRepository.findSoHoKhauBySoHK(soHK, tt);
    }

    @Override
    public void deleteById(Integer integer) {
        soHoKhauRepository.deleteById(integer);
    }

    @Override
    public void delete(SoHoKhau entity) {
        soHoKhauRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        soHoKhauRepository.deleteAllById(integers);
    }
}
