package com.vanthuandev.doanphanmem.controllers.police;

import com.vanthuandev.doanphanmem.pojos.NhanKhau;
import com.vanthuandev.doanphanmem.pojos.NhanKhauTamTru;
import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import com.vanthuandev.doanphanmem.pojos.SoHoKhau;
import com.vanthuandev.doanphanmem.service.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/congan**")
@Slf4j
public class PoliceController {

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
    private LoaiHoSoService loaiHoSoService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession httpSession) {
        httpSession.setAttribute("loaiHoSos", loaiHoSoService.findAll());
        return "congan/welcome";
    }


    @RequestMapping(value = "/hoso",method = RequestMethod.GET)
    public String hoso(Model model) {
        return "congan/hoso";
    }

    @RequestMapping(value = "/hoso/them-nhankhau",method = RequestMethod.GET)
    public String addNhanKhau(Model model, @RequestParam(value = "soHK", required = false) Integer soHK) {
        log.info("" + soHK);
        model.addAttribute("nhanKhauThuongTru", new NhanKhauThuongTru());
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("tonGiaos", tonGiaoService.findAll());
        model.addAttribute("hocVans", hocVanService.findAll());
        model.addAttribute("quanHes", quanHeService.findAll());
        model.addAttribute("soHK", soHK);
        return "congan/nhankhau-thuongtru";
    }

    @RequestMapping(value="/quanly", method = RequestMethod.GET)
    public String quanly(Model model) {
        List<NhanKhauThuongTru> nhanKhauThuongTruList = nhanKhauThuongTruService.findAllByTrangThai(1);
        model.addAttribute("nhanKhauThuongKhauTru", nhanKhauThuongTruList);
        return "congan/quanly";
    }
    @RequestMapping(value="/quanly/timkiem", method = RequestMethod.GET)
    public String timKiem(Model model, @RequestParam(value = "search", required = false) String search) {
        List<NhanKhauThuongTru> nhanKhauThuongTruList = null;
        if(search != null && !search.isEmpty()) {
            nhanKhauThuongTruList = nhanKhauThuongTruService.search(search.trim(), 1);
        } else {
            nhanKhauThuongTruList = nhanKhauThuongTruService.findAllByTrangThai(1);
        }
        model.addAttribute("nhanKhauThuongKhauTru", nhanKhauThuongTruList);
        return "congan/quanly";
    }


    @RequestMapping(value="/danh-sach-chi-tiet-nhan-khau-thuong-tru", method = RequestMethod.GET)
    public String danhSachChiTietNhanLhauThuongTru(Model model,
                                                   @RequestParam(value = "soHK", required = false) Integer soHK) {
        List<NhanKhauThuongTru> nktt = nhanKhauThuongTruService.findAllBySoHKAndTrangThai(soHK,1);
        model.addAttribute("soHoKhau", soHK);
        model.addAttribute("nhanKhauThuongKhauTru", nktt);
        return "congan/list-nk-thuong-tru";
    }

    @RequestMapping(value="/danh-sach-chi-tiet-nhan-khau-thuong-tru-bi-xoa", method = RequestMethod.GET)
    public String danhSachChiTietNhanKhauThuongTruBiXoa(Model model,
                                                   @RequestParam(value = "soHK", required = false) Integer soHK) {
        List<NhanKhauThuongTru> soHoKhau = nhanKhauThuongTruService.findAllBySoHKAndTrangThai(soHK,0);
        model.addAttribute("soHoKhau", soHK);
        model.addAttribute("nhanKhauThuongKhauTru", soHoKhau);
        return "congan/lish-xoa-thuong-tru";
    }

    @RequestMapping(value="/chi-tiet-nhan-khau", method = RequestMethod.GET)
    public String chiTietNhanKhauThuongTru(Model model,
                                           @RequestParam(value = "maNK", required = false) Integer maNK,
                                           @RequestParam(value = "soHK", required = false) Integer soHK) {
        Optional<NhanKhauThuongTru> nktt = nhanKhauThuongTruService.findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(maNK,soHK,1);

        model.addAttribute("nhanKTT", nktt.get());

        return "congan/chi-tiet-nktt";
    }

    @RequestMapping(value="/chi-tiet-nhan-khau/edit", method = RequestMethod.GET)
    public String chiTietNhanKhauThuongTruEdit(Model model,
                                           @RequestParam(value = "maNK", required = false) Integer maNK) {
        Optional<NhanKhau> nhanKhau = nhanKhauService.findById(maNK);
        model.addAttribute("nhanKhau", nhanKhau.get());
        model.addAttribute("danTocs", danTocService.findAll());
        model.addAttribute("tonGiaos", tonGiaoService.findAll());
        model.addAttribute("hocVans", hocVanService.findAll());
        model.addAttribute("quanHes", quanHeService.findAll());
        NhanKhauThuongTru nhanKhauThuongTru = nhanKhau.get().getNhanKhauThuongTrus().iterator().next();
        model.addAttribute("soHK", nhanKhauThuongTru.getSoHoKhau().getSoHK());

        return "congan/nhankhau-thuongtru-edit";
    }

    @RequestMapping(value="/chi-tiet-nhan-khau/delete", method = RequestMethod.GET)
    public String xoaThuongTru(Model model,
                                           @RequestParam(value = "maNK", required = false) Integer maNK,
                                           @RequestParam(value = "soHK", required = false) Integer soHK) {
        Optional<NhanKhauThuongTru> nktt = nhanKhauThuongTruService.findNhanKhauThuongTruByNhanKhauAndSoHoKhauAndTrangThai(maNK,soHK,1);

        model.addAttribute("nhanKTT", nktt.get());

        return "congan/xoa-mot-nktt";
    }

    @RequestMapping(value = "/tach-nhan-khau", method = RequestMethod.GET)
    public String tachNhanKhau(Model model,
                               @RequestParam(value = "soHK", required = false) Integer soHK) {
        List<NhanKhauThuongTru> nktt = nhanKhauThuongTruService.findAllBySoHKAndTrangThai(soHK,1);
        model.addAttribute("soHK", soHK);
        model.addAttribute("nhanKhauThuongKhauTru", nktt);
        return "congan/tach-nhan-khau-thuong-tru";
    }

    // chinh sua so ho khau
    @RequestMapping(value = "/hoso/edit-hokhau", method = RequestMethod.GET)
    public String chinhSuaHoKhau(Model model, @RequestParam(value = "soHK") Integer soHK) {
        SoHoKhau soHoKhau = soHoKhauService.findById(soHK).get();
        model.addAttribute("hoKhau", soHoKhau);
        return "congan/edit-hoso";
    }

    @RequestMapping(value = "/hoso/capnhathoso", method = RequestMethod.POST)
    public String capNhatHoSoHoKhau(Model model, @ModelAttribute SoHoKhau soHoKhau) {
        SoHoKhau newHK = soHoKhauService.findById(soHoKhau.getSoHK()).get();
        newHK.setToSo(soHoKhau.getToSo());
        newHK.setNgayNopLuu(soHoKhau.getNgayNopLuu());
        newHK.setNoiDangKyThuongTru(soHoKhau.getNoiDangKyThuongTru());
        newHK.setNgayCapNhat(new Date());
        soHoKhauService.save(newHK);
        return "redirect:/congan/quanly";
    }

    @RequestMapping(value = "/hoso/xoa-hokhau", method = RequestMethod.GET)
    public String xoaHoKhau(Model model, @RequestParam(value = "soHK") Integer soHK) {
        soHoKhauService.deleteById(soHK);
        return "redirect:/congan/quanly";
    }
}
