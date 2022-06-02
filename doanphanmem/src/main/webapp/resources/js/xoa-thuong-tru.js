

let btnDelete = document.getElementById('xoaThuongTru')


btnDelete.onclick = function () {
    let object = {};
    let formData = new FormData(document.getElementById('myForm'))
    let isValue = true;
    formData.forEach(function(value, key){
        object[key] = value;
        if(!value)
            isValue = false;
    });
    if(!isValue) {
        alert("Vui lòng nhập đầy đủ thông tin")
    } else {
        console.log(object)
        fetch("/congan/api/delete-nhankhauthuongtru", {
            method: 'delete',
            body: JSON.stringify(object),
            headers: {
                "Content-Type": "application/json"
            }
        }).then( res => {
            console.info(res);
            return res.json();

        }).then( data => {
            if(data) {
                window.location.href = `/congan/danh-sach-chi-tiet-nhan-khau-thuong-tru?soHK=${data}`
            } else {
                alert("Thất bại")
            }
        });

    }

}