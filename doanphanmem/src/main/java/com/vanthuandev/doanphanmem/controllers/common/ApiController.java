package com.vanthuandev.doanphanmem.controllers.common;

import com.vanthuandev.doanphanmem.pojos.DanToc;
import com.vanthuandev.doanphanmem.pojos.HocVan;
import com.vanthuandev.doanphanmem.pojos.QuanHe;
import com.vanthuandev.doanphanmem.pojos.TonGiao;
import com.vanthuandev.doanphanmem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private NhanKhauTamTruService nhanKhauTamTruService;

    @Autowired
    private DanTocService danTocService;

    @Autowired
    private QuanHeService quanHeService;

    @Autowired
    private HocVanService hocVanService;

    @Autowired
    private TonGiaoService tonGiaoService;

    @GetMapping(value = "/api/quanhe")
    public List<QuanHe> allQuanhe() {
        return quanHeService.findAll();
    }

    @GetMapping(value = "/api/hocvan")
    public List<HocVan> allHocVan()
    {
        return hocVanService.findAll();
    }
    @GetMapping(value = "/api/tongiao")
    public List<TonGiao> allTonGiao()
    {
        return tonGiaoService.findAll();
    }

    @GetMapping(value = "/api/dantoc")
    public List<DanToc> allDanToc() {
        return danTocService.findAll();
    }

    @GetMapping(value = "/api/max-nhan-khau-tam-tru")
    public Integer maxNhanKhauTamTru() {
        return nhanKhauTamTruService.max();
    }


}
