package com.vanthuandev.doanphanmem.repository.impl;

import com.vanthuandev.doanphanmem.pojos.*;
import com.vanthuandev.doanphanmem.repository.NhanKhauCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class NhanKhauCustomRepositoryImpl implements NhanKhauCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<NhanKhauThuongTru> filterNhanKhauThuongTru(Map<String, String> map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NhanKhauThuongTru> cq = cb.createQuery(NhanKhauThuongTru.class);
        Root rootNK = cq.from(NhanKhau.class);
        Root rootNKTT = cq.from(NhanKhauThuongTru.class);
        Root rootDT = cq.from(DanToc.class);
        Root rootSHK = cq.from(SoHoKhau.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(rootNKTT.get("nhanKhau"), rootNK.get("maNK")));
        predicates.add(cb.equal(rootNKTT.get("soHoKhau"), rootSHK.get("soHK")));
        predicates.add(cb.equal(rootNK.get("danToc"), rootDT.get("maDT")));
        cq.select(rootNKTT);

        if(map.get("hoVaTen") != null && !map.get("hoVaTen").isEmpty()) {
            predicates.add(cb.like(rootNK.get("hoVaTen"), String.format("%%%s%%", map.get("hoVaTen"))));
        }
        if(map.get("sinhTuNgay") != null) {
            try {
                predicates.add(cb.greaterThanOrEqualTo(rootNK.get("ngaySinh"), sdf.parse(map.get("sinhTuNgay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(map.get("sinhDenNgay") != null) {
            try {
                predicates.add(cb.lessThanOrEqualTo(rootNK.get("ngaySinh"), sdf.parse(map.get("sinhDenNgay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(map.get("dangKyXaThuongTru") != null && !map.get("dangKyXaThuongTru").isEmpty()) {
            predicates.add(cb.like(rootSHK.get("noiDangKyThuongTru"), String.format("%%%s%%", map.get("dangKyXaThuongTru"))));
        }
        if(map.get("gioiTinh") != null) {
            predicates.add(cb.equal(rootNK.get("gioiTinh"), Integer.parseInt(map.get("gioiTinh") )));
        }
        if(map.get("dangKyTuNgay") != null) {
            try {
                predicates.add(cb.greaterThanOrEqualTo(rootNK.get("ngayDKTT"), sdf.parse(map.get("dangKyTuNgay") )));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(map.get("dangKyDenNgay") != null) {
            try {
                predicates.add(cb.lessThanOrEqualTo(rootNK.get("ngayDKTT"), sdf.parse(map.get("dangKyDenNgay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(map.get("ngheNghiep") != null && !map.get("ngheNghiep").isEmpty()) {
            predicates.add(cb.like(rootNK.get("ngheNghiep"), String.format("%%%s%%", map.get("ngheNghiep"))));
        }
        if(map.get("cmnd") != null && !map.get("cmnd").isEmpty()) {
            predicates.add(cb.like(rootNK.get("cmnd"), String.format("%%%s%%", map.get("cmnd"))));
        }
        if(map.get("danToc") != null && !map.get("danToc").isEmpty()) {
            predicates.add(cb.equal(rootDT.get("maDT"), Integer.parseInt(map.get("danToc"))));
        }
        predicates.add(cb.equal(rootNKTT.get("trangThai"), 1));
        cq.where(predicates.toArray(new Predicate[]{}));
        cq.orderBy(cb.asc(rootSHK.get("soHK")));
        Query query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<NhanKhauTamTru> filterNhanKhauTamTru(Map<String, String> map) {
        return null;
    }


}
