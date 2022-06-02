package com.vanthuandev.doanphanmem.load;

import com.vanthuandev.doanphanmem.pojos.LoaiHoSo;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoaiHoSoConfig {

    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Bean
    CommandLineRunner commandLineRunnerLoaiHoSo() {
        return args -> {
            loaiHoSoService.saveAll(List.of(
                   new LoaiHoSo(1, "Đăng ký thường trú"),
                   new LoaiHoSo(2, "Đăng ký chuyển khẩu"),
                   new LoaiHoSo(3, "Đăng ký nhập hộ khẩu"),
                   new LoaiHoSo(4, "Đăng ký tách hộ khẩu"),
                   new LoaiHoSo(5, "Đăng ký giấy tạm trú, tạm vắng"),
                   new LoaiHoSo(6, "Đăng ký cấp giấy khai sinh"),
                   new LoaiHoSo(7, "Đăng ký cấp giấy chứng tử")
            ));
        };
    }
}
