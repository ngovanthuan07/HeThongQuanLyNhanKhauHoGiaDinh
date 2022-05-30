package com.vanthuandev.doanphanmem.load;

import com.vanthuandev.doanphanmem.pojos.HocVan;
import com.vanthuandev.doanphanmem.pojos.QuanHe;
import com.vanthuandev.doanphanmem.service.HocVanService;
import com.vanthuandev.doanphanmem.service.QuanHeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HocVanConfig {
    @Autowired
    private HocVanService hocVanService;

    @Bean
    CommandLineRunner commandLineRunnerHocVan() {
        return args -> {
            int id = 1;
            hocVanService.saveAll(List.of(
                  new HocVan(id++, "Không có"),
                  new HocVan(id++, "Nhà trẻ"),
                  new HocVan(id++, "Mẫu giáo"),
                  new HocVan(id++, "Tiểu học"),
                  new HocVan(id++, "Trung học cơ sở"),
                  new HocVan(id++, "Trung học phổ thông"),
                  new HocVan(id++, "Trường, lớp dạy nghề"),
                  new HocVan(id++, "Sơ cấp"),
                  new HocVan(id++, "Trung cấp"),
                  new HocVan(id++, "Cao đẳng"),
                  new HocVan(id++, "Đại học"),
                  new HocVan(id++, "Tiến sĩ"),
                  new HocVan(id++, "Cử nhân"),
                  new HocVan(id++, "Kĩ sư"),
                  new HocVan(id++, "Khác")
            ));
        };
    }
}
