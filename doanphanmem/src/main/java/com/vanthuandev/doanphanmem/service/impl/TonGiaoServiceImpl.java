package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.TonGiao;
import com.vanthuandev.doanphanmem.repository.TonGiaoRepository;
import com.vanthuandev.doanphanmem.service.TonGiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TonGiaoServiceImpl implements TonGiaoService {
    @Autowired
    private TonGiaoRepository tonGiaoRepository;

    @Override
    public <S extends TonGiao> S save(S entity) {
        return tonGiaoRepository.save(entity);
    }

    @Override
    public <S extends TonGiao> List<S> saveAll(Iterable<S> entities) {
        return tonGiaoRepository.saveAll(entities);
    }


    @Override
    public List<TonGiao> findAll() {
        return tonGiaoRepository.findAll();
    }
}
