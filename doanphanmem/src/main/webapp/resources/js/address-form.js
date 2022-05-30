import {
  getTinhThanh,
  getQuanHuyenByIdTinhThanh,
  getPhuongXaByIdQuanHuyen,
} from "./address.js";

let tagHtmlModal;

let tagTinhThanhModal= document.getElementById("tinhthanh")

getTinhThanh((isArray) => {
    isArray.unshift({ma_tinh_thanh: "", ten_tinh_thanh: "Vui lòng chọn tỉnh thành phố"})
    tagTinhThanhModal.innerHTML = isArray.map(item => {
            return `
                <option value="${item.ma_tinh_thanh}">${item.ten_tinh_thanh}</option>
            `
    }).join(" ")
})

tagTinhThanhModal.onchange = handleSelectTinhThanh

let tagQuanHuyenModal= document.getElementById("quanhuyen")

function handleSelectTinhThanh() {
    if(this.value) {
        getQuanHuyenByIdTinhThanh(parseInt(this.value), (isArray) => {

            isArray.unshift({ma_quan_huyen: "", ten_quan_huyen: "Vui lòng chọn quận huyện"})
            
            tagQuanHuyenModal.innerHTML = isArray.map(item => {
                return `
                    <option value="${item.ma_quan_huyen}">${item.ten_quan_huyen}</option>
                `
            }).join(" ")
        })
    }
}
let tagPhuongXaModal= document.getElementById("phuongxa")

tagQuanHuyenModal.onchange = handleSelectQuanHuyen

function handleSelectQuanHuyen() {
    if(this.value) {
        getPhuongXaByIdQuanHuyen(parseInt(this.value), (isArray) => {
            isArray.unshift({ma_phuong_xa: "", ten_phuong_xa: "Vui lòng chọn quận huyện"})
            
            tagPhuongXaModal.innerHTML = isArray.map(item => {
                return `
                    <option value="${item.ma_phuong_xa}">${item.ten_phuong_xa}</option>
                `
            }).join(" ")
        })
    }
}

// modal-form

let chooseAddress = document.getElementById("chooseAddress")

chooseAddress.onclick = function() {
    let qg = document.getElementById("quocgia")
    let tt = document.getElementById("tinhthanh")
    let qh = document.getElementById("quanhuyen")
    let px = document.getElementById("phuongxa")
    let ctdc = document.getElementById("chitietdiachimodal")
    let messageModal = document.querySelector(".text-message-modal");
    // if(ctdc.value && px.value && qh.value && tt.value && qg.value)
    // {  
        let fullAddress = `${ctdc.value ? ctdc.value.concat(", ") : ''} ${px.value ? px.options[px.selectedIndex].text.concat(", ") : ''} ${qh.value ? qh.options[qh.selectedIndex].text.concat(", ") : ''} ${tt.value ? tt.options[tt.selectedIndex].text.concat(", "): ''} ${qg.value} `.trim()
        
        fullAddress = fullAddress.split(" ")
                                .filter(f => f !== '')
                                .reduce((acc, cur) => acc += " " + cur, '').trim()

        console.log(fullAddress);
        messageModal.innerHTML = ""
        let attribute = document.createAttribute("data-dismiss")
        attribute.value = "modal"
        chooseAddress.setAttributeNode(attribute)
        tagHtmlModal.value = fullAddress
    // } else {
    //     messageModal.innerHTML = "Vui lòng không được để trống"
    // }
}

let modalForm = document.querySelectorAll("[modal-form]")
modalForm.forEach((item, index) => {
    item.onclick = function() {
        let tt = document.getElementById("tinhthanh").value = ""
        let qh = document.getElementById("quanhuyen").value = ""
        let px = document.getElementById("phuongxa").value = ""
        let ctdc = document.getElementById("chitietdiachimodal").value = ""
        chooseAddress.removeAttribute("data-dismiss")
        tagHtmlModal = item;
    }
})

