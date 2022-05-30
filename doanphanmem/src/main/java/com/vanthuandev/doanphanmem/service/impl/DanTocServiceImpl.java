package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.DanToc;
import com.vanthuandev.doanphanmem.repository.DanTocRepository;
import com.vanthuandev.doanphanmem.service.DanTocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DanTocServiceImpl implements DanTocService {
    @Autowired
    private DanTocRepository danTocRepository;


    @Override
    public <S extends DanToc> S save(S entity) {
        return danTocRepository.save(entity);
    }

    @Override
    public <S extends DanToc> List<S> saveAll(Iterable<S> entities) {
        return danTocRepository.saveAll(entities);
    }

    @Override
    public List<DanToc> findAll() {
        return danTocRepository.findAll();
    }
}
