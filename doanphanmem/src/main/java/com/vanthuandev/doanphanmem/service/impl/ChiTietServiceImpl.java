package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.ChiTiet;
import com.vanthuandev.doanphanmem.repository.ChiTietRepository;
import com.vanthuandev.doanphanmem.service.ChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChiTietServiceImpl implements ChiTietService {
    @Autowired
    private ChiTietRepository chiTietRepository;


    @Override
    public <S extends ChiTiet> List<S> saveAll(Iterable<S> entities) {
        return chiTietRepository.saveAll(entities);
    }


}
