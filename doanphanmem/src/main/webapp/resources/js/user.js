let tbody = []

function myObject(maChiTiet, tuNgayThangNam, choO, ngheNghieNoiLamViecHienTai) {
    return {
        maChiTiet,
        tuNgayThangNam,
        choO,
        ngheNghieNoiLamViecHienTai
    }
}

function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}

function checkUuidv4(arr) {
    let uuid = uuidv4();
    while (arr.includes(uuid)) {
        uuid = checkUuidv4()
    }
    return uuid
}

function addRow() {
    let uuid = checkUuidv4(tbody)
    tbody.push(
        myObject(uuid, undefined, undefined, undefined)
    )
    console.log(tbody)
    render()
}

function changerValue1(tag, id) {
    let index = tbody.findIndex(item => item.maChiTiet === id)
    tbody[index] = {
        ...tbody[index],
        tuNgayThangNam: tag.value
    }
}
function changerValue2(tag, id) {
    let index = tbody.findIndex(item => item.maChiTiet === id)
    tbody[index] = {
        ...tbody[index],
        choO: tag.value
    }
}
function changerValue3(tag, id) {
    let index = tbody.findIndex(item => item.maChiTiet === id)
    tbody[index] = {
        ...tbody[index],
        ngheNghieNoiLamViecHienTai: tag.value
    }
}

function deleteRow(r) {
    let index = tbody.findIndex(item => item.maChiTiet === r)
    tbody.splice(index, 1)
    console.log(tbody)
    render()
}

function render() {
    let element = document.getElementById("tbody")
    let html = tbody.map(item => {
        return `
                            <tr class="myRow">
                                   <td>
                                        <input class="form-control" name="tuNgayThangNam" 
                                                onchange="changerValue1(this, '${item.maChiTiet}')" value="${item.tuNgayThangNam ?? ''}"/>
                                   </td>
                                   <td>
                                         <input class="form-control" type="text" name="choO" 
                                                onchange="changerValue2(this, '${item.maChiTiet}')" value="${item.choO ?? ''}">
                                   </td>
                                   <td>
                                          <input class="form-control" type="text" name="ngheNghieNoiLamViecHienTai" 
                                                onchange="changerValue3(this, '${item.maChiTiet}')" value="${item.ngheNghieNoiLamViecHienTai ?? ''}">
                                   </td>
                                   <td class="text-center">
                                       <i class="fa-solid fa-trash text-danger" onclick="deleteRow('${item.maChiTiet}')"></i>
                                   </td>
                            </tr>
            `

    })
    element.innerHTML = html.join("")
}

function checkTable() {
    return tbody.every(t => (t.tuNgayThangNam !== undefined && t.choO !== undefined && t.ngheNghieNoiLamViecHienTai !== undefined)
                            && (t.tuNgayThangNam !== '' && t.choO !== '' && t.ngheNghieNoiLamViecHienTai !== ''))
}
var form = new  Validator('#form-nhan-khau');
form.onSubmit = function (formData) {
    if (handleValidateForTable()) {
        delete formData['choO']
        delete formData['tuNgayThangNam']
        delete formData['ngheNghieNoiLamViecHienTai']

        if(Array.isArray(tbody) && tbody.length > 0)
        {
            formData = {
                ...formData,
                chiTiets: tbody
            }
        }
        console.log(JSON.stringify(formData))
        fetch("/nguoidung/api/add-nhankhau", {
            method: 'post',
            body: JSON.stringify(formData),
            headers: {
                "Content-Type": "application/json"
            }
        }).then( res => {
            console.info(res);
            return res.json();

        }).then( data => {
            let isCheck =  window.confirm(data);
            if(isCheck) {
                window.location.reload();
            }
        });
    }
}
function handleValidateForTable() {
    let table = document.getElementsByClassName("notification-mytable");
    if(!checkTable())
    {
        table[0].innerHTML = `<span class="text-danger">Không được để trống</span>`
        return false;
    } else {
        table[0].innerHTML = ``;
        return  true;
    }
}