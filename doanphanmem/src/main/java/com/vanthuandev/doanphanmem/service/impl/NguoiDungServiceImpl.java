package com.vanthuandev.doanphanmem.service.impl;

import com.vanthuandev.doanphanmem.constants.ApplicationRole;
import com.vanthuandev.doanphanmem.pojos.NguoiDung;
import com.vanthuandev.doanphanmem.repository.NguoiDungRepository;
import com.vanthuandev.doanphanmem.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("nguoiDungService")
@Transactional
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;


    @Override
    public NguoiDung getNguoiDungById(int nguoiDungId) {
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(nguoiDungId);
        if(!nguoiDung.isPresent())
        {
            throw new IllegalStateException();
        }
        return nguoiDung.get();
    }

    @Override
    public String addNguoiDung(NguoiDung nguoiDung) {
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findNguoiDungByUsername(nguoiDung.getUsername());
        if(nguoiDungOptional.isPresent()) {
            return "Tên người dùng đã tồn tại";
        }
        try {
            nguoiDung.setTrangThai(1);
            nguoiDung.setPassword(passwordEncoder.encode(nguoiDung.getPassword()));
            nguoiDungRepository.save(nguoiDung);
            return "Cấp tài khoản thành công";
        }
        catch (Exception e)
        {
            return "Lỗi thệ thống";
        }
    }

    @Override
    public Page<NguoiDung> findNguoiDungByUsernameOrCmndContaining(String username, String cmnd, Pageable pageable) {
        return nguoiDungRepository.findNguoiDungByUsernameOrCmndContaining(username, cmnd, pageable);
    }

    @Override
    public Page<NguoiDung> findNguoiDungByUsernameContaining(String username, Pageable pageable) {
        return nguoiDungRepository.findNguoiDungByUsernameContaining(username, pageable);
    }

    @Override
    public Page<NguoiDung> findNguoiDungByCmndContaining(String cmnd, Pageable pageable) {
        return nguoiDungRepository.findNguoiDungByCmndContaining(cmnd, pageable);
    }

    @Override
    public Page<NguoiDung> findAll(Pageable pageable) {
        return nguoiDungRepository.findAll(pageable);
    }


    @Override
    public String updateNguoiDung(NguoiDung nguoiDung, String keyword) {
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findNguoiDungByUsername(nguoiDung.getUsername());
        if(!nguoiDungOptional.isPresent()) {
            return "Tên người dùng không tồn tại";
        }
        try {
            if(!keyword.contains("updateStatus"))
                nguoiDung.setPassword(passwordEncoder.encode(nguoiDung.getPassword()));
            nguoiDungRepository.save(nguoiDung);
            return "Cập nhật thành công";
        }
        catch (Exception e)
        {
            return "Lỗi thệ thống";
        }
    }

    @Override
    public NguoiDung getNguoiDung(String username) {
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findNguoiDungByUsername(username);
        if(!nguoiDungOptional.isPresent()) {
            throw new UsernameNotFoundException("Nguoi dung does not exits");
        }
        return nguoiDungOptional.get();
    }

    @Override
    public Page<NguoiDung> findNguoiDungByUsernameAndTrangThaiContaining(String username, int trangThai, Pageable pageable) {
        return nguoiDungRepository.findNguoiDungByUsernameAndTrangThaiContaining(username, trangThai, pageable);
    }

    @Override
    public Page<NguoiDung> findNguoiDungByTrangThaiContaining(int trangThai, Pageable pageable) {
        return nguoiDungRepository.findNguoiDungByTrangThaiContaining(trangThai, pageable);
    }



    @Override
    public List<NguoiDung> findAll() {
        return nguoiDungRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NguoiDung> nguoiDungOptional = nguoiDungRepository.findNguoiDungByUsername(username);
        if(!nguoiDungOptional.isPresent()) {
            throw new UsernameNotFoundException("Nguoi dung does not exits");
        }
        if(nguoiDungOptional.get().getTrangThai() == 0) {
            throw new UsernameNotFoundException("User has been locked");
        }
        NguoiDung nguoiDung = nguoiDungOptional.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        nguoiDung.getVaiTros().forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getCode()))
        );

        return new org.springframework.security.core.userdetails.
                    User(nguoiDung.getUsername(), nguoiDung.getPassword(), authorities);
    }
}
