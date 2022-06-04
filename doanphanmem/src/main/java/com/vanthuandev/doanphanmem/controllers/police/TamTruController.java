package com.vanthuandev.doanphanmem.controllers.police;


import com.vanthuandev.doanphanmem.pdf.PDFGenerator;
import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/congan/tamtru**")
public class TamTruController {
    @Autowired
    private DanTocService danTocService;

    @Autowired
    private TonGiaoService tonGiaoService;

    @Autowired
    private QuanHeService quanHeService;

    @Autowired
    private HocVanService hocVanService;

    @Autowired
    private NhanKhauThuongTruService nhanKhauThuongTruService;

    @Autowired
    private NhanKhauService nhanKhauService;

    @Autowired
    private SoHoKhauService soHoKhauService;

    @Autowired
    private NhanKhauTamTruService nhanKhauTamTruService;



    @GetMapping("/tamtrucanhan")
    public String add_tam_tru_ca_nhan(Model model) {
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("tonGiaos", tonGiaoService.findAll());
        model.addAttribute("hocVans", hocVanService.findAll());
        model.addAttribute("quanHes", quanHeService.findAll());
        return "congan/tamtru/add-tam-tru-ca-nhan";
    }
    @GetMapping("/tamtruho")
    public String add_tam_tru_ho(Model model) {
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("tonGiaos", tonGiaoService.findAll());
        model.addAttribute("hocVans", hocVanService.findAll());
        model.addAttribute("quanHes", quanHeService.findAll());
        return "congan/tamtru/add-tam-tru-ho";
    }

    @GetMapping("/add-them-thanh-vien-tam-tru")
    public String add_them_thanh_vien_tam_tru(Model model,
                                              @RequestParam(value = "maTT", required = false) Integer maTT) {
        model.addAttribute("maTT", maTT);
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("tonGiaos", tonGiaoService.findAll());
        model.addAttribute("hocVans", hocVanService.findAll());
        model.addAttribute("quanHes", quanHeService.findAll());
        return "congan/tamtru/add-them-thanh-vien-tam-tru";
    }


    @GetMapping("/quanlytramtru")
    public String danhSachNhanKhauTamTru(Model model) {
        List<NhanKhauTamTru> nktt =  nhanKhauTamTruService.findAllByNhanKhauTamTruByTrangThai(1);
        model.addAttribute("nhanKhauTamTrus", nktt);
        return "congan/tamtru/danh-sach-nhan-khau-tam-tru";
    }

    @GetMapping("/quanlytamtru/timkiem")
    public String search(Model model, @RequestParam("search") String search) {
        List<NhanKhauTamTru> nktt = null;
        if(search != null && !search.isEmpty()) {
            nktt = nhanKhauTamTruService.search(search.trim(), 1);
        } else {
            nktt = nhanKhauTamTruService.findAllByNhanKhauTamTruByTrangThai(1);
        }
        model.addAttribute("nhanKhauTamTrus", nktt);
        return "congan/tamtru/danh-sach-nhan-khau-tam-tru";
    }



    @GetMapping("/danh-sach-chi-tiet-tam-tru")
    public String danhSachChiTietNhanKhauTamTru(Model model,
                                                @RequestParam(value = "maTT", required = false) Integer maTT) {
        List<NhanKhauTamTru> nhanKhauTamTrus =  nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndTrangThai(maTT, 1);
        model.addAttribute("nhanKhauTamTrus", nhanKhauTamTrus);
        return "congan/tamtru/danh-sach-chi-tiet-nhan-khau-tam-tru";
    }
    @GetMapping("/danh-sach-chi-tiet-tam-tru/chitiet")
    public String chiTietNhanKhauTamTru(Model model,
                                        @RequestParam(value = "maTT", required = false) Integer maTT,
                                        @RequestParam(value = "maNK", required = false) Integer maNK) {
        Optional<NhanKhauTamTru> nhanKhauTamTru =  nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndMaNK(maTT, maNK);
        if(nhanKhauTamTru.isPresent()) {
            model.addAttribute("nhanKhauTamTru", nhanKhauTamTru.get());
            model.addAttribute("maTT", nhanKhauTamTru.get().getNhanKhauTamTruPK().getMaTT());
            model.addAttribute("maNK", nhanKhauTamTru.get().getNhanKhauTamTruPK().getMaNK());
        }
        return "congan/tamtru/dsnktt-chitiet";
    }
    @GetMapping("/danh-sach-chi-tiet-tam-tru/giahan")
    public String chiTietNhanKhauTamTru_GiaHan(Model model,
                                        @RequestParam(value = "maTT", required = false) Integer maTT,
                                        @RequestParam(value = "maNK", required = false) Integer maNK) {
        Optional<NhanKhauTamTru> nhanKhauTamTru =  nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndMaNK(maTT, maNK);
        if(nhanKhauTamTru.isPresent()) {
            model.addAttribute("nhanKhauTamTru", nhanKhauTamTru.get());
            model.addAttribute("maTT", nhanKhauTamTru.get().getNhanKhauTamTruPK().getMaTT());
            model.addAttribute("maNK", nhanKhauTamTru.get().getNhanKhauTamTruPK().getMaNK());
        }
        return "congan/tamtru/dsnktt-giahan";
    }

    @PostMapping("/danh-sach-chi-tiet-tam-tru/giahan")
    public String chiTietNhanKhauTamTru_GiaHan_ThoiGian(Model model,
                                               @RequestParam(value = "maTT", required = false) Integer maTT,
                                               @RequestParam(value = "maNK", required = false) Integer maNK,
                                               @RequestParam(value = "ngayGiaHan", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayGiaHan,
                                               @RequestParam(value = "tamTruDenNgay", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date tamTruDenNgay,
                                               @RequestParam(value = "lyDo", required = false) String lyDo) {
        Optional<NhanKhauTamTru> nhanKhauTamTru =  nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndMaNK(maTT, maNK);
        if(nhanKhauTamTru.isPresent()) {
            NhanKhauTamTru nktt = nhanKhauTamTru.get();
            nktt.setNgayGiaHan(ngayGiaHan);
            nktt.setTamTruDenNgay(tamTruDenNgay);
            nktt.setLyDo(lyDo);
            NhanKhauTamTru save = nhanKhauTamTruService.save(nktt);
            if(save != null) {
                return "redirect:/congan/tamtru/danh-sach-chi-tiet-tam-tru?maTT=" + maTT;
            }
        }
        return "congan/tamtru/dsnktt-giahan";
    }

    @GetMapping("/danh-sach-chi-tiet-tam-tru/chinhsua")
    public String chiTietNhanKhauTamTru_chinhsua(Model model,
                                                 @RequestParam(value = "maTT", required = false) Integer maTT,
                                                 @RequestParam(value = "maNK", required = false) Integer maNK) {
        Optional<NhanKhauTamTru> nhanKhauTamTru =  nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndMaNK(maTT, maNK);
        if(nhanKhauTamTru.isPresent()) {
            // truong lien quan
            model.addAttribute("danTocs", danTocService.findAll());
            model.addAttribute("tonGiaos", tonGiaoService.findAll());
            model.addAttribute("hocVans", hocVanService.findAll());
            model.addAttribute("quanHes", quanHeService.findAll());
            // end
            model.addAttribute("nhanKhauTamTru", nhanKhauTamTru.get());
            model.addAttribute("nhanKhau", nhanKhauTamTru.get().getNhanKhau());
            model.addAttribute("maTT", nhanKhauTamTru.get().getNhanKhauTamTruPK().getMaTT());
            model.addAttribute("maNK", nhanKhauTamTru.get().getNhanKhauTamTruPK().getMaNK());
        }
        return "congan/tamtru/dsnktt-chinhsua";
    }

    @GetMapping("/danh-sach-chi-tiet-tam-tru/xoa")
    public String chiTietNhanKhauTamTru_XoaMotNhanKhauTamTru(Model model,
                                               @RequestParam(value = "maTT", required = false) Integer maTT,
                                               @RequestParam(value = "maNK", required = false) Integer maNK) {
        Optional<NhanKhauTamTru> nhanKhauTamTru =  nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndMaNK(maTT, maNK);
        if(nhanKhauTamTru.isPresent()) {
            NhanKhauTamTru nktt = nhanKhauTamTru.get();
            nktt.setTrangThai(0);
            nhanKhauTamTruService.save(nktt);
        }
        return "redirect:/congan/tamtru/danh-sach-chi-tiet-tam-tru?maTT=" + maTT;
    }
    @GetMapping("/xoa-so-tam-tru")
    public String xoaTamTru(Model model, @RequestParam(value = "maTT", required = false) Integer maTT) {
        List<NhanKhauTamTru> nhanKhauTamTrus = nhanKhauTamTruService.findNhanKhauTamTruByMaTTAndTrangThai(maTT,1);
        if(!nhanKhauTamTrus.isEmpty()) {
            nhanKhauTamTrus.forEach(nktt -> nktt.setTrangThai(0));
            nhanKhauTamTruService.saveAll(nhanKhauTamTrus);
        }
        return "redirect:/congan/tamtru/quanlytramtru";
    }
}
