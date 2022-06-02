

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
        console.log(isValue)
        fetch("/congan/api/delete-nhankhauthuongtru", {
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

}