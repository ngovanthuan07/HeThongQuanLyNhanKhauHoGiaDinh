import {TOM_TAT_BAN_THAN, TOM_TAT_GIA_DINH} from "./CKEDITOR_Data.js"

// CKEDITOR.replace('tomTatBanThan');
// CKEDITOR.replace('tomTatGiaDinh');
//
// $(document).ready(function() {
//     $('.selectpicker').selectpicker()
// })
// CKEDITOR.instances['tomTatBanThan'].setData(TOM_TAT_BAN_THAN);
// CKEDITOR.instances['tomTatGiaDinh'].setData(TOM_TAT_GIA_DINH);

let form = new Validator('#myForm')

form.onSubmit = function (formData) {

    fetch("/congan/api/add-sohokhau", {
        method: 'post',
        body: JSON.stringify(formData),
        headers: {
            "Content-Type": "application/json"
        }
    }).then( res => {
        console.info(res);
        return res.json();

    }).then( data => {
        if(data['soHK']) {
            window.location.href = "/congan/hoso/them-nhankhau?soHK="+data['soHK']
        } else {
            alert("Thất bại")
        }
    });
}




