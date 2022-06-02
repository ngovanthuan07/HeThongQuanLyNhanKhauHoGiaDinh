package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import com.vanthuandev.doanphanmem.repository.DangKyHoSoRepository;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DangKyHoSoServiceImpl implements DangKyHoSoService {
    @Autowired
    private DangKyHoSoRepository dangKyHoSoRepository;

    @Override
    public <S extends DangKyHoSo> S save(S entity) {
        return dangKyHoSoRepository.save(entity);
    }

    @Override
    public <S extends DangKyHoSo> List<S> saveAll(Iterable<S> entities) {
        return dangKyHoSoRepository.saveAll(entities);
    }

    @Override
    public List<DangKyHoSo> findAllById(Iterable<Integer> integers) {
        return dangKyHoSoRepository.findAllById(integers);
    }

    @Override
    public Optional<DangKyHoSo> findById(Integer integer) {
        return dangKyHoSoRepository.findById(integer);
    }

    @Override
    public List<DangKyHoSo> findAllByMaLHSAndTrangThai(int maLHS, int trangThai) {
        return dangKyHoSoRepository.findAllByMaLHSAndTrangThai(maLHS, trangThai);
    }
}
