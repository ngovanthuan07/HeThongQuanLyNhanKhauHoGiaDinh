package com.vanthuandev.doanphanmem.load;

import com.vanthuandev.doanphanmem.pojos.QuanHe;
import com.vanthuandev.doanphanmem.repository.QuanHeRepository;
import com.vanthuandev.doanphanmem.service.QuanHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuanHeConfig {
    @Autowired
    private QuanHeService quanHeService;

    @Bean
    CommandLineRunner commandLineRunnerQuanHe() {
        return args -> {
            quanHeService.saveAll(List.of(
                    new QuanHe(1, "Chủ hộ"),
                    new QuanHe(2, "Chồng"),
                    new QuanHe(3, "Vợ"),
                    new QuanHe(4, "Cha đẻ"),
                    new QuanHe(5, "Mẹ đẻ"),
                    new QuanHe(6, "Cha nuôi"),
                    new QuanHe(7, "Mẹ nuôi"),
                    new QuanHe(8, "Con đẻ"),
                    new QuanHe(10, "Con nuôi"),
                    new QuanHe(11, "Ông nội"),
                    new QuanHe(12, "Bà nội"),
                    new QuanHe(13, "Ông ngoại"),
                    new QuanHe(14, "Bà ngoại"),
                    new QuanHe(15, "Anh ruột"),
                    new QuanHe(16, "Chị ruột"),
                    new QuanHe(17, "Em ruột"),
                    new QuanHe(18, "Cháu ruột"),
                    new QuanHe(19, "Cụ nội"),
                    new QuanHe(20, "Cụ ngoại"),
                    new QuanHe(21, "Bác ruột"),
                    new QuanHe(22, "Chú ruột"),
                    new QuanHe(23, "Cậu ruột"),
                    new QuanHe(24, "Cô ruột"),
                    new QuanHe(25, "Dì ruột"),
                    new QuanHe(26, "Chắt ruột"),
                    new QuanHe(27, "Người giám"),
                    new QuanHe(28, "Hộ"),
                    new QuanHe(29, "Ở nhờ"),
                    new QuanHe(30, "Ơ Mượn"),
                    new QuanHe(31, "Ơ thuê"),
                    new QuanHe(32, "Cùng ở nhờ"),
                    new QuanHe(33, "Cùng ở thuê"),
                    new QuanHe(34, "Cùng ở mượn"),
                    new QuanHe(35, "Quan hệ khác")
            ));
        };
    }
}
