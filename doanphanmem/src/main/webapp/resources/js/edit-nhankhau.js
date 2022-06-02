let tomTatBanThanValue = document.getElementById('tomTatBanThan').value
let tomTatGiaDinhValue = document.getElementById('tomTatGiaDinh').value


CKEDITOR.replace('tomTatBanThan');
CKEDITOR.replace('tomTatGiaDinh');

CKEDITOR.instances['tomTatBanThan'].setData(tomTatBanThanValue);
CKEDITOR.instances['tomTatGiaDinh'].setData(tomTatGiaDinhValue);

let quanHe = {
    maQH: $('#selectQuanHe option:selected').val() ?? undefined,
    tenQH: $('#selectQuanHe option:selected').text() ?? undefined,
}
console.log("Quan he: ", quanHe)
let hocVan = {
    maHV: $('#selectHocVan option:selected').val() ?? undefined,
    tenHV: $('#selectHocVan option:selected').text() ?? undefined,
}
console.log("Hoc van: ", hocVan)
let danToc = {
    maDT: $('#selectDanToc option:selected').val() ?? undefined,
    tenDT: $('#selectDanToc option:selected').text() ?? undefined,
}
console.log("Dan toc: ", danToc)
let tonGiao = {
    maTG: $('#selectTonGiao option:selected').val() ?? undefined,
    tenTG: $('#selectTonGiao option:selected').text() ?? undefined,
}
console.log("Ton giao: ", tonGiao)
$('#selectQuanHe').change(function (e) {
    var options  = $('#selectQuanHe option:selected');
    $(options).each(function(){
        quanHe = {
            maQH: $(this).val(),
            tenQH: $(this).text(),
        }
    });
})
$('#selectHocVan').change(function (e) {
    var options  = $('#selectHocVan option:selected');
    $(options).each(function(){
        hocVan = {
            maHV: $(this).val(),
            tenHV: $(this).text(),
        }
    });

})
$('#selectDanToc').change(function (e) {
    var options  = $('#selectDanToc option:selected');
    $(options).each(function(){
        danToc = {
            maDT: $(this).val(),
            tenDT: $(this).text(),
        }
    });
})
$('#selectTonGiao').change(function (e) {
    var options  = $('#selectTonGiao option:selected');
    $(options).each(function(){
        tonGiao = {
            maTG: $(this).val(),
            tenTG: $(this).text(),
        }
    });
})

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
    fetch("/congan/api/edit-nhankhauthuongtru", {
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
            alert(data)
        } else {
            alert("Thất bại")
        }
    });
}




