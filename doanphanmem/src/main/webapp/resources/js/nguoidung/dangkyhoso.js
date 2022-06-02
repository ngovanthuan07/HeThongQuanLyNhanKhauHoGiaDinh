CKEDITOR.replace('mauDon');

loaiHoSo = {}

$('#loaiHoSo').change(function (e) {
    var options  = $('#loaiHoSo option:selected');
    $(options).each(function(){
        loaiHoSo = {
            maLHS: $(this).val(),
            tenLHS: $(this).text(),
        }
    });
    console.log("Loai so: ", loaiHoSo)
})

let form = new Validator('#from');

form.onSubmit = function (formData) {
    let mauDon = CKEDITOR.instances['mauDon'].getData();
    formData = {
        ...formData,
        'loaiHoSo': loaiHoSo,
        'mauDon': mauDon
    }
    fetch("/nguoidung/api/send-ho-so", {
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
            alert("Thành công")
            window.location.reload()
        } else {
            alert("Thất bại")
        }
    });
}