var orderTabId = 0;

//Counter page
function addCounterPage() {
    if (orderTabId < 5) {
        orderTabId++;
        renderButtonOrderTab(orderTabId);
        renderCounterPage(orderTabId);
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tối đa 5 hóa đơn!'
        });
        return;
    }
}

function renderButtonOrderTab(orderTabId) {
    var buttonOrderTab = "<button class=\"btn btn-add-order\" data-orderTabId='" + orderTabId + "'>Hóa đơn " + orderTabId + "</button>";

    $("#listCounter").append(buttonOrderTab);

    // Khi click vào button
    $('.btn-add-order').click(function () {
        // Xóa class 'btn-active' từ tất cả các button
        $('.btn-add-order').removeClass('btn-active');

        // Thêm class 'btn-active' cho button được click
        $(this).addClass('btn-active');
    });

    // Kích hoạt sự kiện click cho nút mới thêm vào
    $('.btn-add-order[data-orderTabId="' + orderTabId + '"]').click();
}

function renderCounterPage(orderTabId){
    var counterPage = "<div class=\"d-flex\" data-orderTabId='" + orderTabId + "'>\n" +
        "            <div class=\"col-md-8\">\n" +
        "                <div class=\"tile\">\n" +
        "                    <h3 class=\"tile-title\">Phần mềm bán hàng</h3>\n" +
        "                    <!--Cart-->\n" +
        "                    <table class=\"table table-hover table-bordered cart\" id=\"cartTable\">\n" +
        "                        <thead>\n" +
        "                        <tr>\n" +
        "                            <th class=\"stt\">STT</th>\n" +
        "                            <th class=\"ten_san_pham\">Tên Sản Phẩm</th>\n" +
        "                            <th class=\"anh\">Ảnh</th>\n" +
        "                            <th class=\"mau_sac\">Màu Sắc</th>\n" +
        "                            <th class=\"kich_thuoc\">Size</th>\n" +
        "                            <th class=\"so_luong\">Số Lượng</th>\n" +
        "                            <th class=\"gia_ban\">Giá Bán</th>\n" +
        "                            <th class=\"tinh_nang\">Tính Năng</th>\n" +
        "                        </tr>\n" +
        "                        </thead>\n" +
        "\n" +
        "                        <tbody id=\"listCart\">\n" +
        "\n" +
        "                        </tbody>\n" +
        "\n" +
        "                    </table>\n" +
        "                </div>\n" +
        "\n" +
        "                <div class=\"tile\">\n" +
        "                    <h3 class=\"tile-title\">Danh sách sản phẩm</h3>\n" +
        "\n" +
        "                    <input type=\"text\" id=\"myInput\" onkeyup=\"search()\"\n" +
        "                           placeholder=\"Nhập thông tin sản phẩm để tìm kiếm...\">\n" +
        "                    <div class=\"du--lieu-san-pham\">\n" +
        "\n" +
        "                        <!--Danh sách sản phẩm-->\n" +
        "                        <table class=\"table table-hover table-bordered js-copytextarea\" cellpadding=\"0\"\n" +
        "                               cellspacing=\"0\"\n" +
        "                               border=\"0\"\n" +
        "                               id=\"sampleTable\">\n" +
        "                            <thead>\n" +
        "                            <tr>\n" +
        "                                <th class=\"stt\">STT</th>\n" +
        "                                <th class=\"ten_san_pham\">Tên Sản Phẩm</th>\n" +
        "                                <th class=\"anh\">Ảnh</th>\n" +
        "                                <th class=\"mau_sac\">Màu Sắc</th>\n" +
        "                                <th class=\"kich_thuoc\">Size</th>\n" +
        "                                <th class=\"so_luong\">Số Lượng</th>\n" +
        "                                <th class=\"gia_ban\">Giá Bán</th>\n" +
        "                                <th class=\"tinh_nang\">Tính Năng</th>\n" +
        "                            </tr>\n" +
        "                            </thead>\n" +
        "\n" +
        "                            <tbody th:if=\"${listProductDetail.empty}\">\n" +
        "                            <tr>\n" +
        "                                <td colspan=\"8\" class=\"text-center\">Danh sách trống!</td>\n" +
        "                            </tr>\n" +
        "                            </tbody>\n" +
        "\n" +
        "                            <tbody>\n" +
        "                            <tr th:each=\"productDetail, index : ${listProductDetail}\">\n" +
        "                                <td class=\"stt\" th:text=\"${index.index + 1}\"></td>\n" +
        "                                <td class=\"ten_san_pham\" th:text=\"${productDetail.name}\"></td>\n" +
        "                                <td class=\"anh\"><img th:src=\"${productDetail.image}\" alt=\"Hình ảnh sản phẩm\"></td>\n" +
        "                                <td class=\"mau_sac\" th:text=\"${productDetail.colorName}\"></td>\n" +
        "                                <td class=\"kich_thuoc\" th:text=\"${productDetail.sizeName}\"></td>\n" +
        "                                <td class=\"so_luong\" th:text=\"${productDetail.quantity}\"></td>\n" +
        "                                <td class=\"gia_ban\" id=\"gia_ban\"\n" +
        "                                    th:text=\"${#numbers.formatDecimal((productDetail.discount == 0 ?\n" +
        "                                    productDetail.price :\n" +
        "                                    (productDetail.price/100*(100 - productDetail.discount))), 0, 'POINT', 0, 'POINT')}\"\n" +
        "                                ></td>\n" +
        "                                <td class=\"table-td-center tinh_nang\">\n" +
        "                                    <button class=\"btn btn-success add-button\" type=\"button\"\n" +
        "                                            th:attr=\"data-product-detail-id=${productDetail.id},\n" +
        "                                                 id=#{${productDetail.id}}\"\n" +
        "                                            onclick=\"addToCart(this)\">\n" +
        "                                        <i class=\"fa-solid fa-plus fa-2xl add-icon\"></i>\n" +
        "                                    </button>\n" +
        "                                </td>\n" +
        "                            </tr>\n" +
        "                            </tbody>\n" +
        "                        </table>\n" +
        "\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"col-md-4\">\n" +
        "                <div class=\"tile\">\n" +
        "                    <h3 class=\"tile-title\">Thông tin thanh toán</h3>\n" +
        "\n" +
        "                    <div class=\"row\">\n" +
        "                        <div class=\"form-group col-md-12\">\n" +
        "                            <label class=\"control-label\">Họ tên khách hàng:</label>\n" +
        "                            <div class=\"d-flex\">\n" +
        "                                <select class=\"form-control\" id=\"select-customer\" style=\"margin-right: 10px\">\n" +
        "\n" +
        "                                </select>\n" +
        "                                <button id=\"showFormCustomer\" class=\"btn btn-primary btn-them\" data-toggle=\"modal\"\n" +
        "                                        data-target=\"#exampleModalCenter\"><i class=\"fas fa-user-plus\"></i>\n" +
        "                                </button>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"form-group  col-md-12\">\n" +
        "                            <label class=\"control-label\">Nhân viên bán hàng:</label>\n" +
        "                            <select class=\"form-control\" id=\"select-staff\">\n" +
        "                                <option value=\"\" disabled selected hidden>--- Chọn nhân viên bán hàng ---</option>\n" +
        "                                <option th:each=\"staff : ${listStaff}\" th:value=\"${staff.id}\"\n" +
        "                                        th:text=\"${staff.name}\"></option>\n" +
        "                            </select>\n" +
        "                        </div>\n" +
        "                        <div class=\"form-group  col-md-12\">\n" +
        "                            <label class=\"control-label\">Ghi chú đơn hàng:</label>\n" +
        "                            <textarea id=\"input-note\" class=\"form-control\" rows=\"4\"\n" +
        "                                      placeholder=\"Ghi chú thêm đơn hàng\"></textarea>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "\n" +
        "                    <div class=\"row\">\n" +
        "                        <div class=\"form-group  col-md-12\">\n" +
        "                            <label class=\"control-label\">Hình thức thanh toán:</label>\n" +
        "                            <select class=\"form-control\" id=\"select-shopping\" required>\n" +
        "                                <option value=\"Trả tiền mặt tại quầy\">Trả tiền mặt tại quầy</option>\n" +
        "                                <option value=\"Thanh toán chuyển khoản\">Thanh toán chuyển khoản</option>\n" +
        "                            </select>\n" +
        "                        </div>\n" +
        "                        <div class=\"form-group col-md-12\">\n" +
        "                            <label class=\"control-label\">Tổng cộng thanh toán:</label>\n" +
        "                            <p class=\"control-all-money-total\" id=\"tong-cong-thanh-toan\">0</p>\n" +
        "                        </div>\n" +
        "                        <div class=\"form-group  col-md-12\">\n" +
        "                            <label class=\"control-label\">Khách hàng đưa tiền:</label>\n" +
        "                            <input class=\"form-control\" id=\"khach-hang-dua-tien\" type=\"number\" value=\"0\" min=\"0\"\n" +
        "                                   onchange=\"tinhNo()\">\n" +
        "                        </div>\n" +
        "                        <div class=\"form-group  col-md-12\">\n" +
        "                            <label class=\"control-label\">Trả lại khách:</label>\n" +
        "                            <p class=\"control-all-money\" id=\"tra-lai-khach\">0</p>\n" +
        "                        </div>\n" +
        "                        <div class=\"form-group  col-md-12\">\n" +
        "                            <label class=\"control-label\">Khách hàng còn thiếu:</label>\n" +
        "                            <p class=\"control-all-money\" id=\"khach-hang-con-thieu\">0</p>\n" +
        "                        </div>\n" +
        "                        <div class=\"tile-footer col-md-12\">\n" +
        "                            <button class=\"btn btn-primary luu-san-pham\" type=\"button\" onclick=\"saveOrder()\"> Lưu đơn\n" +
        "                                hàng\n" +
        "                            </button>\n" +
        "                            <button class=\"btn btn-primary luu-va-in\" type=\"button\">Lưu và in hóa đơn</button>\n" +
        "                            <a class=\"btn btn-secondary luu-va-in\" href=\"index.html\">Quay về</a>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>";
    $("#form-counter").append(counterPage);
}
