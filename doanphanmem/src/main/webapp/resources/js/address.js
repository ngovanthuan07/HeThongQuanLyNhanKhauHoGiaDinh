import {initializeApp} from "https://www.gstatic.com/firebasejs/9.8.1/firebase-app.js";
import { getDatabase,ref,set, get,child,update,remove} from "https://www.gstatic.com/firebasejs/9.8.1/firebase-database.js";

const firebaseConfig = {
    apiKey: "AIzaSyAZGWqKxgl1JELFLhoK32cgzy8kg0DyhAI",
    authDomain: "fir-js-bb6c1.firebaseapp.com",
    projectId: "fir-js-bb6c1",
    storageBucket: "fir-js-bb6c1.appspot.com",
    messagingSenderId: "446047679833",
    appId: "1:446047679833:web:bee979aa5120d2bc89c848",
};
// Initialize Firebase
const app = initializeApp(firebaseConfig);


const db = getDatabase();

const isRef = ref(db)

export function getTinhThanh(callback) {
    get(child(isRef, "tinh_thanh"))
        .then((snapshot) => {
            if (snapshot.exists()) {
                let isValue = snapshot.val();
                callback(isValue)
            } else {
                console.log("Not data found")
            }
        })
        .catch((error) => {
            console.log("unsuccessful, error " + error)
        })
}

export function getQuanHuyenByIdTinhThanh(id, callback) {
    get(child(isRef, "quan_huyen"))
        .then((snapshot) => {
            if (snapshot.exists()) {
                let isValue = snapshot.val().filter(item => item.ma_tinh_thanh === id)
                console.log(isValue);
                callback(isValue)
            } else {
                alert("Not data found")
            }
        })
        .catch((error) => {
            alert("unsuccessful, error " + error)
        })
}

export function getPhuongXaByIdQuanHuyen(id, callback) {
    get(child(isRef, "phuong_xa"))
        .then((snapshot) => {
            if (snapshot.exists()) {
                let isValue = snapshot.val().filter(item => item.ma_quan_huyen === id)
                console.log(isValue)
                callback(isValue)
            } else {
                alert("Not data found")
            }
        })
        .catch((error) => {
            alert("unsuccessful, error " + error)
        })
}

