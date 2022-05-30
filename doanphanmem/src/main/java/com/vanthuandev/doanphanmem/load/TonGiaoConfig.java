package com.vanthuandev.doanphanmem.load;

import com.vanthuandev.doanphanmem.pojos.QuanHe;
import com.vanthuandev.doanphanmem.pojos.TonGiao;
import com.vanthuandev.doanphanmem.service.TonGiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TonGiaoConfig {
    @Autowired
    private TonGiaoService tonGiaoService;


    @Bean
    CommandLineRunner commandLineRunnerTonGiao() {
        return args -> {
            int id = 1;
            tonGiaoService.saveAll(List.of(
                   new TonGiao(id++, "Không có"),
                   new TonGiao(id++, "Phật giáo"),
                   new TonGiao(id++, "Công giáo"),
                   new TonGiao(id++, "Tin lành"),
                   new TonGiao(id++, "Cao đài"),
                   new TonGiao(id++, "Phật giáo Hòa Hảo"),
                   new TonGiao(id++, "Hồi giáo"),
                   new TonGiao(id++, "Tôn giáo Baha’I"),
                   new TonGiao(id++, "Tịnh độ Cư sỹ Phật hội"),
                   new TonGiao(id++, "Cơ đốc Phục lâm"),
                   new TonGiao(id++, "Phật giáo Tứ Ân Hiếu nghĩa"),
                   new TonGiao(id++, "Minh Sư đạo"),
                   new TonGiao(id++, "Minh lý đạo - Tam Tông Miếu"),
                   new TonGiao(id++, "Bàlamôn giáo"),
                   new TonGiao(id++, "Mặc môn"),
                   new TonGiao(id++, "Phật giáo Hiếu Nghĩa Tà Lơn"),
                   new TonGiao(id++, "Bửu Sơn Kỳ")
            ));
        };
    }
}
