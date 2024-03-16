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
    // Gửi yêu cầu AJAX
    await $.ajax({
        type: "POST",
        url: "/cart-detail/decrement/" + productId,
        contentType: "application/json",
        success: function (response) {
            console.log("Giảm số lượng thành công! (" + productId + " - 1)");

            var quantity = parseInt($("#quantity_" + productId).val());
            if (quantity > 1) {
                $("#quantity_" + productId).val(quantity - 1);
            }

            calculateTotal();
        },
        error: function (error) {
            console.error("Lỗi khi giảm số lượng:", error);
        }
    });
}

async function incrementQuantity(productId) {
    // Gửi yêu cầu AJAX
    await $.ajax({
        type: "POST",
        url: "/cart-detail/increment/" + productId,
        contentType: "application/json",
        success: function (response) {
            console.log("Tăng số lượng thành công! (" + productId + " + 1)");

            var quantity = parseInt($("#quantity_" + productId).val());
            $("#quantity_" + productId).val(quantity + 1);

            calculateTotal();
        },
        error: function (error) {
            console.error("Lỗi khi tăng số lượng:", error);
        }
    });
}