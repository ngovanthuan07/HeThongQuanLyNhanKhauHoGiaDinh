package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.LoaiHoSo;
import com.vanthuandev.doanphanmem.repository.LoaiHoSoRepository;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoaiHoSoServiceImpl implements LoaiHoSoService {
    @Autowired
    private LoaiHoSoRepository loaiHoSoRepository;

    @Override
    public <S extends LoaiHoSo> List<S> saveAll(Iterable<S> entities) {
        return loaiHoSoRepository.saveAll(entities);
    }

    @Override
    public List<LoaiHoSo> findAll() {
        return loaiHoSoRepository.findAll();
    }

    @Override
    public Optional<LoaiHoSo> findById(Integer integer) {
        return loaiHoSoRepository.findById(integer);
    }
}
