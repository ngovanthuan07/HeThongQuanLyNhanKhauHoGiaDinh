/**
 * Yêu cẩu phải sử dụng boostrap 4
 * thêm 3 attribute vào một thẻ input nào đó để sử dung modal:
                                    data-toggle="modal"
                                    data-target="#exampleModal"
                                    modal-form
 * thêm thẻ div vào dưới thẻ body project:         
                                    <div id = "my-modal"></div>
 * thêm script: 
                                    <script src="./js/modal-form.js"></script>
                                    <script type="module" src="./js/address-form.js"></script>
 */
document.getElementById("my-modal").innerHTML = `
        <div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
        >
        <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title">Chọn địa chỉ nơi sinh</h5>
            <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
            >
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
            <div class="text-center">
                <span class="text-message-modal text-danger"></span>

            </div>
                <div class="form-group">
                <label>Quốc gia</label>
                <input
                    type="text"
                    class="form-control"
                    id="quocgia"
                    name="quocgia"
                    value="Việt Nam"
                    disabled
                />
                
                </div>

                <div class="form-group">
                <label>Tỉnh thành phố</label>
                <select
                    class="form-control"
                    id="tinhthanh"
                    name="tinhthanh"
                ></select>
                </div>

                <div class="form-group">
                <label>Quận huyện</label>
                <select class="form-control" id="quanhuyen"></select>
                </div>

                <div class="form-group">
                <label>Phường xã</label>
                <select class="form-control" id="phuongxa"></select>
                </div>

                <div class="form-group">
                <label>Chi tiết địa chỉ</label>
                <textarea 
                    id="chitietdiachimodal"
                    name="chitietdiachi"
                    class="form-control"
                ></textarea>
                </div>
            </form>
            </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="chooseAddress">Chọn</button>
            <button
                type="button"
                class="btn btn-secondary"
                data-dismiss="modal"
            >
                Đóng
            </button>
            </div>
        </div>
        </div>
        </div>


`;
