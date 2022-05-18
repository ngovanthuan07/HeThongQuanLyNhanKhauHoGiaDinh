package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.VaiTro;
import com.vanthuandev.doanphanmem.repository.VaiTroRepository;
import com.vanthuandev.doanphanmem.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VaiTroServiceImpl implements VaiTroService {
    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public List<VaiTro> findAll() {
        return vaiTroRepository.findAll(Sort.by(Sort.Direction.DESC, "maVaiTro"));
    }

    @Override
    public VaiTro findById(int maVaiTro) {
        return vaiTroRepository.findById(maVaiTro).get();
    }
}
