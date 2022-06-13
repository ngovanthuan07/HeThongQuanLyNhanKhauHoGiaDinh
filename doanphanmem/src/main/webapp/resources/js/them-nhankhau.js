import {TOM_TAT_BAN_THAN, TOM_TAT_GIA_DINH} from "./CKEDITOR_Data.js"

CKEDITOR.replace('tomTatBanThan');
CKEDITOR.replace('tomTatGiaDinh');

let quanHe = {}
let hocVan = {}
let danToc = {}
let tonGiao = {}

$('#selectQuanHe').change(function (e) {
    var options  = $('#selectQuanHe option:selected');
    $(options).each(function(){
        quanHe = {
            maQH: $(this).val(),
            tenQH: $(this).text(),
        }
    });
    console.log("Quan he: ", quanHe)
})
$('#selectHocVan').change(function (e) {
    var options  = $('#selectHocVan option:selected');
    $(options).each(function(){
        hocVan = {
            maHV: $(this).val(),
            tenHV: $(this).text(),
        }
    });
    console.log("Hoc van: ", hocVan)
})
$('#selectDanToc').change(function (e) {
    var options  = $('#selectDanToc option:selected');
    $(options).each(function(){
        danToc = {
            maDT: $(this).val(),
            tenDT: $(this).text(),
        }
    });
    console.log("Dan toc: ", danToc)
})
$('#selectTonGiao').change(function (e) {
    var options  = $('#selectTonGiao option:selected');
    $(options).each(function(){
        tonGiao = {
            maTG: $(this).val(),
            tenTG: $(this).text(),
        }
    });
    console.log("Ton giao: ", tonGiao)
})




CKEDITOR.instances['tomTatBanThan'].setData(TOM_TAT_BAN_THAN);
CKEDITOR.instances['tomTatGiaDinh'].setData(TOM_TAT_GIA_DINH);

let form = new Validator('#myForm')

form.onSubmit = function (formData) {
    let soHK = document.getElementById("soHK").value
    let tomTatBanThan = CKEDITOR.instances['tomTatBanThan'].getData();
    let tomTatGiaDinh = CKEDITOR.instances['tomTatGiaDinh'].getData();
    if(Array.isArray(formData['hoNgoaiHuyenDen'])) {
        formData['hoNgoaiHuyenDen'] =  1 * formData['hoNgoaiHuyenDen'][0]
    } else {
        formData['hoNgoaiHuyenDen'] = 0
    }
    if(Array.isArray(formData['nhanKhauMoiSinh'])) {
        formData['nhanKhauMoiSinh'] =  1 * formData['nhanKhauMoiSinh'][0]
    } else {
        formData['nhanKhauMoiSinh'] = 0
    }
    formData = {
        ...formData,
        quanHe,
        hocVan,
        danToc,
        tonGiao,
        tomTatBanThan,
        tomTatGiaDinh,
        "_id": soHK
    }
    console.log(formData)

    fetch("/congan/api/add-nhankhauthuongtru", {
        method: 'post',
        body: JSON.stringify(formData),
        headers: {
            "Content-Type": "application/json"
        }
    }).then( res => {
        console.info(res);
        return res.json();

    }).then( data => {
        if(data.includes("Success")) {
            let isCheck =  window.confirm("Thêm thành công");
            if(isCheck) {
                let r = data.split("|")
                window.location.replace("/congan/danh-sach-chi-tiet-nhan-khau-thuong-tru?soHK="+ r[1]);
            } else {
                window.location.reload();
            }
        } else {
            alert("Tách hộ khẩu thất bại")
        }
    });

}




