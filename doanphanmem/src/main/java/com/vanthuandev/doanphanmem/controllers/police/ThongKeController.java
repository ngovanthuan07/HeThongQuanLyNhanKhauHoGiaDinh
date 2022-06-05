package com.vanthuandev.doanphanmem.controllers.police;

import com.vanthuandev.doanphanmem.pojos.DangKyHoSo;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.service.DangKyHoSoService;
import com.vanthuandev.doanphanmem.service.LoaiHoSoService;
import com.vanthuandev.doanphanmem.service.NhanKhauTamTruService;
import com.vanthuandev.doanphanmem.service.NhanKhauThuongTruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/congan/thongke**")
public class ThongKeController {
    @Autowired
    private LoaiHoSoService loaiHoSoService;

    @Autowired
    private DangKyHoSoService dangKyHoSoService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;

    @Autowired
    private NhanKhauTamTruService nhanKhauTamTruService;

    @GetMapping("/thong-ke-thuong-tru")
    public String thongKeThuongTru(Model model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<DangKyHoSo> hoSos = dangKyHoSoService.findAllByMaLHSAndTrangThai(1, 1);
        List<NhanKhauThuongTru> nhanKhauThuongTrus = nhanKhauThuongTruService.findAllByTrangThai(1);
        List<NhanKhau> nhanKhauNus = nhanKhauThuongTrus.stream()
                .filter(nktt -> nktt.getNhanKhau().getGioiTinh() == 2)
                .map(nktt-> nktt.getNhanKhau()).collect(Collectors.toList());

        List<NhanKhau> nhanKhauLonHonMuoiBons = nhanKhauThuongTrus.stream()
                        .filter(nktt -> Period.between(
                                LocalDate.parse(simpleDateFormat.format(nktt.getNhanKhau().getNgaySinh())),
                                LocalDate.now()).getYears() >= 14)
                        .map(nktt-> nktt.getNhanKhau()).collect(Collectors.toList());

        model.addAttribute("hoSo", hoSos.isEmpty() ? 0 : hoSos.size());
        model.addAttribute("nhanKhau", nhanKhauThuongTrus.isEmpty() ? 0 : nhanKhauThuongTrus.size() + 1);
        model.addAttribute("nhanKhauNu", nhanKhauNus.isEmpty() ? 0 : nhanKhauNus.size() + 1);
        model.addAttribute("nhanKhauLonHonMuoiBon", nhanKhauLonHonMuoiBons.isEmpty() ? 0 : nhanKhauLonHonMuoiBons.size() + 1);
        return "congan/thongke/thongkenktt";
    }

    @GetMapping("/thong-ke-tam-tru")
    public String thongKeTamTru(Model model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<DangKyHoSo> hoSos = dangKyHoSoService.findAllByMaLHSAndTrangThai(5, 1);
        List<NhanKhauTamTru> nhanKhauTamTrus = nhanKhauTamTruService.findAllByNhanKhauTamTruByTrangThai(1);
        List<NhanKhau> nhanKhauNus = nhanKhauTamTrus.stream()
                .filter(nktt -> nktt.getNhanKhau().getGioiTinh() == 2)
                .map(nktt-> nktt.getNhanKhau()).collect(Collectors.toList());
        List<NhanKhau> nhanKhauLonHonMuoiBons = nhanKhauTamTrus.stream()
                .filter(nktt -> Period.between(
                        LocalDate.parse(simpleDateFormat.format(nktt.getNhanKhau().getNgaySinh())),
                        LocalDate.now()).getYears() >= 14)
                .map(nktt-> nktt.getNhanKhau()).collect(Collectors.toList());
        model.addAttribute("hoSo", hoSos.isEmpty() ? 0 : hoSos.size());
        model.addAttribute("nhanKhau", nhanKhauTamTrus.isEmpty() ? 0 : nhanKhauTamTrus.size());
        model.addAttribute("nhanKhauNu", nhanKhauNus.isEmpty() ? 0 : nhanKhauNus.size());
        model.addAttribute("nhanKhauLonHonMuoiBon", nhanKhauLonHonMuoiBons.isEmpty() ? 0 : nhanKhauLonHonMuoiBons.size());

        return "congan/thongke/thongketamtru";
    }
}
