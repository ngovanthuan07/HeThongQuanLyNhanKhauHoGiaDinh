package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.QuanHe;
import com.vanthuandev.doanphanmem.repository.QuanHeRepository;
import com.vanthuandev.doanphanmem.service.QuanHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuanHeServiceImpl implements QuanHeService {
    @Autowired
    private QuanHeRepository quanHeRepository;


    @Override
    public <S extends QuanHe> S save(S entity) {
        return quanHeRepository.save(entity);
    }

    @Override
    public <S extends QuanHe> List<S> saveAll(Iterable<S> entities) {
        return quanHeRepository.saveAll(entities);
    }


    @Override
    public List<QuanHe> findAll() {
        return quanHeRepository.findAll();
    }
}
