package com.vanthuandev.doanphanmem.controllers.police;

import com.vanthuandev.doanphanmem.pdf.PDFGenerator;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.service.DanTocService;
import com.vanthuandev.doanphanmem.service.NhanKhauService;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/congan/baocao**")
public class BaoCaoController {
    @Autowired
    private DanTocService danTocService;

    @Autowired
    private NhanKhauService nhanKhauService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;

    @GetMapping
    private String baocaoThuongTru(Model model) {
        return "/congan/baocao/baocao-thuong-tru.html";
    }

    @GetMapping("/xuat-bao-cao-nhan-khau-thuong-tru")
    private void xuatBaoCaoThuongTru(Model model, HttpServletResponse response,
                       @RequestParam String dangKyXaThuongTru,
                       @RequestParam String dangKyTuNgay,
                       @RequestParam String dangKyDenNgay) {
        // Map dữ liệu
        Map<String, String> map = new HashMap<>();
        map.put("dangKyXaThuongTru", dangKyXaThuongTru);
        map.put("dangKyTuNgay", dangKyTuNgay);
        map.put("dangKyDenNgay", dangKyDenNgay);
        // Sử lý pdf
        response.setContentType("application/pdf;charset=UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List<NhanKhauThuongTru> nhanKhauThuongTruList = nhanKhauThuongTruService.filterNhanKhau(map);
        PDFGenerator generator = new PDFGenerator();
        generator.setNhanKhauThuongTrus(nhanKhauThuongTruList);
        generator.generateNhanKhauThuongTru(response, map);
    }
}
