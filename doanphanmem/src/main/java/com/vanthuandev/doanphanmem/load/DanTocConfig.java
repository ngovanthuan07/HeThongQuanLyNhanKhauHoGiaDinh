package com.vanthuandev.doanphanmem.load;

import com.vanthuandev.doanphanmem.pojos.DanToc;
import com.vanthuandev.doanphanmem.pojos.HocVan;
import com.vanthuandev.doanphanmem.service.DanTocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DanTocConfig {
    @Autowired
    private DanTocService danTocService;

    @Bean
    CommandLineRunner commandLineRunnerDanToc() {
        return args -> {
            int id = 1;
            danTocService.saveAll(List.of(
                    new DanToc(id++, "Kinh"),
                    new DanToc(id++, "Tày"),
                    new DanToc(id++, "Thái"),
                    new DanToc(id++, "Hoa"),
                    new DanToc(id++, "Khơ-me"),
                    new DanToc(id++, "Mường"),
                    new DanToc(id++, "Nùng"),
                    new DanToc(id++, "HMông"),
                    new DanToc(id++, "Dao"),
                    new DanToc(id++, "Gia-rai"),
                    new DanToc(id++, "Ngái"),
                    new DanToc(id++, "Ê-đê"),
                    new DanToc(id++, "Ba na"),
                    new DanToc(id++, "Xơ-Đăng"),
                    new DanToc(id++, "Sán Chay"),
                    new DanToc(id++, "Cơ-ho"),
                    new DanToc(id++, "Chăm"),
                    new DanToc(id++, "Sán Dìu"),
                    new DanToc(id++, "Hrê"),
                    new DanToc(id++, "Mnông"),
                    new DanToc(id++, "Ra-glai"),
                    new DanToc(id++, "Xtiêng"),
                    new DanToc(id++, "Bru-Vân Kiều"),
                    new DanToc(id++, "Thổ"),
                    new DanToc(id++, "Giáy"),
                    new DanToc(id++, "Cơ-tu"),
                    new DanToc(id++, "Gié Triêng"),
                    new DanToc(id++, "Mạ"),
                    new DanToc(id++, "Khơ-mú"),
                    new DanToc(id++, "Co"),
                    new DanToc(id++, "Tà-ôi"),
                    new DanToc(id++, "Chơ-ro"),
                    new DanToc(id++, "Kháng"),
                    new DanToc(id++, "Xinh-mun"),
                    new DanToc(id++, "Hà Nhì"),
                    new DanToc(id++, "Chu ru"),
                    new DanToc(id++, "Lào"),
                    new DanToc(id++, "La Chí"),
                    new DanToc(id++, "La Ha"),
                    new DanToc(id++, "Phù Lá"),
                    new DanToc(id++, "La Hủ"),
                    new DanToc(id++, "Lự"),
                    new DanToc(id++, "Lô Lô"),
                    new DanToc(id++, "Chứt"),
                    new DanToc(id++, "Mảng"),
                    new DanToc(id++, "Pà Thẻn"),
                    new DanToc(id++, "Co Lao"),
                    new DanToc(id++, "Cống"),
                    new DanToc(id++, "Bố Y"),
                    new DanToc(id++, "Si La"),
                    new DanToc(id++, "Pu Péo"),
                    new DanToc(id++, "Brâu"),
                    new DanToc(id++, "Ơ Đu"),
                    new DanToc(id++, "Rơ măm"),
                    new DanToc(id++, "Người nước ngoài"),
                    new DanToc(id++, "Không rõ")
            ));
        };
    }
}
