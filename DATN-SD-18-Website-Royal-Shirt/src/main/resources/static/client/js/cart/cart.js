//Danh sách id cartDetail được chọn để checkout
var listProductChoice = [];

// Xử lý sự kiện khi checkbox thay đổi trạng thái
$('.cart-checkbox').change(function() {
    var cartDetailId = $(this).val(); // Lấy ID của cartDetail từ giá trị của checkbox

    // Kiểm tra xem checkbox có được chọn hay không
    if ($(this).is(':checked')) {
        // Nếu được chọn, thêm ID vào listProductChoice (nếu chưa có)
        if (!listProductChoice.includes(cartDetailId)) {
            listProductChoice.push(cartDetailId);
        }
    } else {
        // Nếu không được chọn, loại bỏ ID khỏi listProductChoice (nếu có)
        var index = listProductChoice.indexOf(cartDetailId);
        if (index !== -1) {
            listProductChoice.splice(index, 1);
        }
    }

    console.log('Danh sách được chọn:', listProductChoice);
});

function saveListProductToCheckout() {
    var listProduct = JSON.parse(localStorage.getItem("listProductChoice")) || {};

    listProduct.listProductChoice = listProductChoice;

    // Lưu lại dữ liệu vào Local Storage
    localStorage.setItem("listProductChoice", JSON.stringify(listProduct));
}

$(document).ready(function () {
    $('.cart-checkbox').change(function () {
        calculateTotal();
    });
});


function calculateTotal() {
    var selectedIds = [];
    $('.cart-checkbox:checked').each(function () {
        selectedIds.push($(this).val());
    });

    $.ajax({
        type: 'POST',
        url: '/sumPrice',
        contentType: 'application/json',
        data: JSON.stringify(selectedIds),
        success: function (response) {
            var formattedTongTien = formatCurrency(response.totalPrice);
            $('#sumPrice').text(formattedTongTien);
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
}

async function decrementQuantity(productId) {
    var quantity = parseInt($("#quantity_" + productId).val());
    if (quantity > 1) {
        // Gửi yêu cầu AJAX
        await $.ajax({
            type: "POST",
            url: "/cart-detail/decrement/" + productId,
            contentType: "application/json",
            success: function (response) {
                console.log("Giảm số lượng thành công! (" + productId + " - 1)");
                $("#quantity_" + productId).val(quantity - 1);
                calculateTotal();
            },
            error: function (error) {
                console.error("Lỗi khi giảm số lượng:", error);
            }
        });
    }
}

async function incrementQuantity(productId) {
    var quantity = parseInt($("#quantity_" + productId).val());
    var maxQuantity = parseInt($('#quantity_' + productId).attr('max'));
    if (quantity < maxQuantity) {
        // Gửi yêu cầu AJAX
        await $.ajax({
            type: "POST",
            url: "/cart-detail/increment/" + productId,
            contentType: "application/json",
            success: function (response) {
                console.log("Tăng số lượng thành công! (" + productId + " + 1)");
                $("#quantity_" + productId).val(quantity + 1);
                calculateTotal();
            },
            error: function (error) {
                console.error("Lỗi khi tăng số lượng:", error);
            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vượt số lượng tồn sản phẩm!'
        });
        return;
    }
}

