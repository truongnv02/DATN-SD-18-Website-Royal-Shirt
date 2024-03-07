var sttCounter = 1;
var listProductCart = [];
var totalPrice = 0;

//Show form customer
$(document).ready(function () {
    $('#showFormCustomer').click(function () {
        $('#CustomerModal').modal('show');
    });

    $('#closeFormCustomer').click(function () {
        $('#customerName').val('');
        $('#customerPhone').val('');
        $('#CustomerModal').modal('hide');
    });

    $('#CustomerModal').on('hidden.bs.modal', function () {
        $('#customerName').val('');
        $('#customerPhone').val('');
    });

    renderListCustomer();
});

function saveCustomer() {
    var customerName = $("#customerName").val().trim();
    var customerPhone = $("#customerPhone").val().trim();
    var status = 0;

    if (!checkInputCustomer(customerName, customerPhone)) {
        return;
    }

    var dataToSend = {
        name: customerName,
        phone: customerPhone,
        status: status
    }

    // Gửi yêu cầu AJAX
    $.ajax({
        type: "POST",
        url: "/admin/rest/customer/add",
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function (response) {
            console.log("Lưu Khách hàng thành công!");

            renderListCustomer();

            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Lưu Khách hàng thành công!',
                didClose: function () {
                    $('#customerName').val('');
                    $('#customerPhone').val('');
                    $('#CustomerModal').modal('hide');
                }
            });

        },
        error: function (error) {
            console.error("Lỗi khi lưu Khách hàng:", error);

            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi lưu Khách hàng!'
            });
        }
    });
}

function checkInputCustomer(customerName, customerPhone) {
    // Kiểm tra xem các trường có rỗng không
    if (customerName === "" || customerPhone === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Thông số kích thước không hợp lệ!'
        });
        return false;
    }

    //Check Kích thước
    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(customerName)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên khách hàng chỉ được chứa chữ cái và khoảng trắng!'
        });
        return false;
    }

    var phoneRegex = /^0\d{9}$/;
    if (!phoneRegex.test(customerPhone)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Số điện thoại không hợp lệ!'
        });
        return false;
    }

    if (!checkDuplicateCustomer(customerPhone)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Số điện thoại đã tồn tại!'
        });
        return false;
    }

    return true;
}

// Check trùng Số điện thoại
function checkDuplicateCustomer(customerPhone) {
    var isDuplicatePhone;
    // Gửi yêu cầu AJAX để kiểm tra trùng Số điện thoại
    $.ajax({
        type: "POST",
        url: "/admin/rest/customer/checkDuplicatePhone",
        contentType: "application/json",
        data: JSON.stringify({phone: customerPhone}),
        async: false,
        success: function (response) {
            isDuplicatePhone = response.isDuplicatePhone;
        },
        error: function (error) {
            console.error("Lỗi khi kiểm tra trùng Số điện thoại:", error);
        }
    });
    return isDuplicatePhone;
}

function renderListCustomer() {
    // Xóa tất cả các option hiện tại trong select
    $('#select-customer')[0].selectize && $('#select-customer')[0].selectize.destroy();
    $('#select-customer').empty();

    $.ajax({
        method: 'GET',
        url: '/admin/rest/customer', // Thay thế ĐƯỜNG_DẪN_API bằng đường dẫn thực tế của bạn
        contentType: "application/json",
        success: function (data) {
            console.log("Lấy danh sách Khách hàng thành công!");
            // Append các option từ dữ liệu API

            $('#select-customer').append('<option value="" disabled selected hidden>--- Chọn khách hàng ---</option>');

            $.each(data, function (index, customer) {
                $('#select-customer').append('<option value="' + customer.id + '">' + customer.name + '</option>');
            });

            $('#select-customer').selectize();
        },
        error: function (error) {
            console.error('Lỗi khi gọi API getListCustomer: ', error);
        }
    });
}

function search() {
    var keyword = $('#myInput').val();
    var dataTable = $('#sampleTable').DataTable();
    dataTable.search(keyword).draw();
}

function addToCart(button) {
    var productDetailId = $(button).data("product-detail-id");
    var productName = $(button).closest("tr").find(".ten_san_pham").text();
    var productImage = $(button).closest("tr").find(".anh img").attr("src");
    var productColor = $(button).closest("tr").find(".mau_sac").text();
    var productSize = $(button).closest("tr").find(".kich_thuoc").text();
    var productPrice = $(button).closest("tr").find(".gia_ban").text();


    // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
    var existingRow = $("#cartTable").find("tr[data-product-detail-id='" + productDetailId + "']");

    if (existingRow.length > 0) {
        // Tăng số lượng nếu đã có trong giỏ hàng
        var quantity = parseInt(existingRow.find(".so_luong input").val()) + 1;
        existingRow.find(".so_luong input").val(quantity);

        // Cập nhật số lượng trong listProductCart
        var existingProduct = listProductCart.find(function (product) {
            return product.id === productDetailId;
        });

        if (existingProduct) {
            existingProduct.quantity = quantity;
        }
    } else {
        var quantity = 1;
        // Thêm dòng mới vào bảng giỏ hàng
        var newRow = "<tr data-product-detail-id='" + productDetailId + "'>" +
            "<td class='stt'>" + sttCounter++ + "</td>" +
            "<td class='ten_san_pham'>" + productName + "</td>" +
            "<td class='anh'><img src='" + productImage + "' alt='Hình ảnh sản phẩm'></td>" +
            "<td class='mau_sac'>" + productColor + "</td>" +
            "<td class='kich_thuoc'>" + productSize + "</td>" +
            "<td class='so_luong' data-quantity='" + quantity + "'><input class='input_so_luong' type='number' value='" + quantity + "' min='1' onchange='updateQuantity(this)'></td>" +
            "<td class='gia_ban' id='gia_ban'>" + productPrice + "</td>" +
            "<td class='tinh_nang'><button class='btn btn-danger remove-button' onclick='removeFromCart(this)'><i class='fa-solid fa-minus fa-2xl remove-icon'></i></button></td>" +
            "</tr>";
        $("#cartTable").append(newRow);

        var productCart = {
            id: productDetailId,
            quantity: quantity,
            price: parseFloat(productPrice.replace(/\./g, ''))
        }

        listProductCart.push(productCart);
    }
    getTotalPrice();
    console.log(listProductCart);
}

function updateQuantity(input) {
    var row = $(input).closest("tr");
    var productDetailId = row.data("product-detail-id");
    var newQuantity = parseInt($(input).val());

    // Cập nhật số lượng trong giỏ hàng
    row.find(".so_luong input").val(newQuantity);

    // Cập nhật số lượng trong listProductCart
    var existingProductIndex = listProductCart.findIndex(function (product) {
        return product.id === productDetailId;
    });

    if (existingProductIndex !== -1) {
        listProductCart[existingProductIndex].quantity = newQuantity;
    }

    getTotalPrice();
    console.log(listProductCart);
}

function removeFromCart(button) {
    var row = $(button).closest("tr");
    var productDetailId = row.data("product-detail-id");

    // Xóa sản phẩm khỏi giỏ hàng
    row.remove();

    // Xóa sản phẩm khỏi listProductCart
    listProductCart = listProductCart.filter(function (product) {
        return product.id !== productDetailId;
    });

    getTotalPrice();
    console.log(listProductCart);
}

function getTotalPrice() {
    // Lặp qua từng sản phẩm trong listProductCart và tính tổng giá
    totalPrice = listProductCart.reduce(function (accumulator, product) {
        return accumulator + (product.price * product.quantity);
    }, 0);

    console.log("Tong tien: " + totalPrice);

    // Định dạng số và hiển thị dấu phân cách hàng nghìn
    var formattedPrice = totalPrice.toFixed(0).replace(/\d(?=(\d{3})+$)/g, "$&.");
    $("#tong-cong-thanh-toan").text(formattedPrice);

    tinhNo();
}

function tinhNo() {
    var tienNo = $("#khach-hang-dua-tien").val() - totalPrice;
    console.log("Tong tien: " + totalPrice);
    console.log("Tien khach tra: " + $("#khach-hang-dua-tien").val());
    console.log("Tien no: " + tienNo);
    console.log(tienNo);

    // Định dạng số và hiển thị dấu phân cách hàng nghìn
    var tienNoFormat = tienNo.toFixed(0).replace(/\d(?=(\d{3})+$)/g, "$&.");

    if (tienNo < 0) {
        $("#khach-hang-con-thieu").text(tienNoFormat);
        $("#tra-lai-khach").text(0);
    } else {
        $("#khach-hang-con-thieu").text(0);
        $("#tra-lai-khach").text(tienNoFormat);
    }
}






