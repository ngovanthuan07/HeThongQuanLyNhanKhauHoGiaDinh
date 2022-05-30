package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.HocVan;
import com.vanthuandev.doanphanmem.repository.HocVanRepository;
import com.vanthuandev.doanphanmem.service.HocVanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HocVanServiceImpl implements HocVanService {
    @Autowired
    private HocVanRepository hocVanRepository;


    @Override
    public <S extends HocVan> S save(S entity) {
        return hocVanRepository.save(entity);
    }


    @Override
    public <S extends HocVan> List<S> saveAll(Iterable<S> entities) {
        return hocVanRepository.saveAll(entities);
    }

    @Override
    public List<HocVan> findAll() {
        return hocVanRepository.findAll();
    }
}
