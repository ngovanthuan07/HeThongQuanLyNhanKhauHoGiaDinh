
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

let form = new Validator('#myForm')
form.onSubmit = function (formData) {
    let nhanKhauTamTru = {
         noiThuongTru : formData['noiThuongTru'],
         noiDangKiTamTru : formData['noiDangKiTamTru'],
         ngayDangKyTamTru : formData['ngayDangKyTamTru'],
         tamTruTuNgay : formData['tamTruTuNgay'],
         tamTruDenNgay : formData['tamTruDenNgay'],
    }
    delete formData['noiThuongTru']
    delete formData['noiDangKiTamTru']
    delete formData['ngayDangKyTamTru']
    delete formData['tamTruTuNgay']
    delete formData['tamTruDenNgay']
    formData = {
        ...formData,
        quanHe,
        hocVan,
        danToc,
        tonGiao,
    }

    let obj = {
        'nhanKhauTamTru': JSON.stringify(nhanKhauTamTru),
        'nhanKhau': JSON.stringify(formData)
    }
    fetch("/congan/api/tamtru/add-tam-tru-ho", {
        method: 'post',
        body: JSON.stringify(obj),
        headers: {
            "Content-Type": "application/json"
        }
    }).then( res => {
        console.info(res);
        return res.json();

    }).then( data => {
        if(data.includes("/congan")) {
            let isCheck =  window.confirm("Bạn có muốn thêm một người trong sổ tạm trú hay không");
            if(isCheck) {
                window.location.replace(data);
            } else {

                window.location.reload();
            }
        } else {
            alert("Thất bại")
        }
    });


}