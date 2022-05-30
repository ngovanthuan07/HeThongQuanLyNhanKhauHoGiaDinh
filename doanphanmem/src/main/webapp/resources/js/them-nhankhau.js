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
    formData = {
        ...formData,
        quanHe,
        hocVan,
        danToc,
        tonGiao,
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
        if(data) {
            console.log(data)
        } else {
            alert("Thất bại")
        }
    });
}




