package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.repository.NhanKhauThuongTruRepository;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NhanKhauThuongTruServiceImpl implements NhanKhauThuongTruService {
    @Autowired
    private NhanKhauThuongTruRepository nhanKhauThuongTruRepository;


    @Override
    public <S extends NhanKhauThuongTru> S save(S entity) {
        return nhanKhauThuongTruRepository.save(entity);
    }


    @Override
    public List<NhanKhauThuongTru> findAllByTrangThai(int trangThai) {
        return nhanKhauThuongTruRepository.findAllByTrangThai(trangThai);
    }

    @Override
    public List<NhanKhauThuongTru> findAll() {
        return nhanKhauThuongTruRepository.findAll();
    }

    @Override
    public List<NhanKhauThuongTru> findAllBySoHKAndTrangThai(int soHK, int trangThai) {
        return nhanKhauThuongTruRepository.findAllBySoHKAndTrangThai(soHK, trangThai);
    }

    @Override
    public Optional<NhanKhauThuongTru> findNhanKhauThuongTruByNhanKhau(int maNK, int trangThai) {
        return nhanKhauThuongTruRepository.findNhanKhauThuongTruByNhanKhau(maNK, trangThai);
    }


    @Override
    public Optional<NhanKhauThuongTru> findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(int maNK, int soHK, int trangThai) {
        return nhanKhauThuongTruRepository.findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(maNK, soHK, trangThai);
    }

    @Override
    public Optional<NhanKhauThuongTru> findById(Integer integer) {
        return nhanKhauThuongTruRepository.findById(integer);
    }

    @Override
    public <S extends NhanKhauThuongTru> List<S> saveAll(Iterable<S> entities) {
        return nhanKhauThuongTruRepository.saveAll(entities);
    }
}
