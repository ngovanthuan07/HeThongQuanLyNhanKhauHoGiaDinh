package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.GiayChungTu;
import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;
import com.vanthuandev.doanphanmem.repository.GiayChungTuRepository;
import com.vanthuandev.doanphanmem.repository.GiayKhaiSinhRepository;
import com.vanthuandev.doanphanmem.service.GiayChungTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GiayChungTuServiceImpl implements GiayChungTuService {
    @Autowired
    private GiayChungTuRepository giayChungTuRepository;

    @Override
    public List<GiayChungTu> findAll() {
        return giayChungTuRepository.findAll();
    }

    @Override
    public List<GiayChungTu> findAllById(Iterable<Integer> integers) {
        return giayChungTuRepository.findAllById(integers);
    }

    @Override
    public <S extends GiayChungTu> S save(S entity) {
        return giayChungTuRepository.save(entity);
    }

    @Override
    public Optional<GiayChungTu> findById(Integer integer) {
        return giayChungTuRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        giayChungTuRepository.deleteById(integer);
    }
}
