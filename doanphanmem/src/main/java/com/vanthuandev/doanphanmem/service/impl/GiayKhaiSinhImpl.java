package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.GiayKhaiSinh;
import com.vanthuandev.doanphanmem.repository.GiayKhaiSinhRepository;
import com.vanthuandev.doanphanmem.service.GiayKhaiSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class GiayKhaiSinhImpl implements GiayKhaiSinhService {

    @Autowired
    private GiayKhaiSinhRepository giayKhaiSinhRepository;


    @Override
    public <S extends GiayKhaiSinh> S save(S entity) {
        return giayKhaiSinhRepository.save(entity);
    }

    @Override
    public Optional<GiayKhaiSinh> findById(Integer integer) {
        return giayKhaiSinhRepository.findById(integer);
    }

    @Override
    public List<GiayKhaiSinh> findAll() {
        return giayKhaiSinhRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        giayKhaiSinhRepository.deleteById(integer);
    }

    @Override
    public List<GiayKhaiSinh> findAllGiayKhaiSinhByHoTenKhaiSinh(String keyword) {
        return giayKhaiSinhRepository.findAllGiayKhaiSinhByHoTenKhaiSinh(keyword);
    }
}
