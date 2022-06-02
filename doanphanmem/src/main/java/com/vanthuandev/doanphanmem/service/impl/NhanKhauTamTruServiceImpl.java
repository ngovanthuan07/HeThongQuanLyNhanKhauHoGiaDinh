package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.repository.NhanKhauTamTruRepository;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauTamTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NhanKhauTamTruServiceImpl implements NhanKhauTamTruService {

    @Autowired
    private NhanKhauTamTruRepository nhanKhauTamTruRepository;

    @Override
    public <S extends NhanKhauTamTru> S save(S entity) {
        return nhanKhauTamTruRepository.save(entity);
    }

    @Override
    public <S extends NhanKhauTamTru> List<S> saveAll(Iterable<S> entities) {
        return nhanKhauTamTruRepository.saveAll(entities);
    }

    @Override
    public Integer max() {
        Integer max = nhanKhauTamTruRepository.max();
        if(max == null) {
            return 0;
        }
        return max;
    }

    @Override
    public List<NhanKhauTamTru> findAllByNhanKhauTamTruByTrangThai(int trangThai) {
        return nhanKhauTamTruRepository.findAllByNhanKhauTamTruByTrangThai(trangThai);
    }

    @Override
    public List<NhanKhauTamTru> findNhanKhauTamTruByMaTTAndTrangThai(int maTT, int trangThai) {
        return nhanKhauTamTruRepository.findNhanKhauTamTruByMaTTAndTrangThai(maTT, trangThai);
    }

    @Override
    public Optional<NhanKhauTamTru> findNhanKhauTamTruByMaTTAndMaNK(int maTT, int maNK) {
        return nhanKhauTamTruRepository.findNhanKhauTamTruByMaTTAndMaNK(maTT, maNK);
    }
}
