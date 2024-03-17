var listProductIdChoice = [];
var listProduct = [];

$(document).ready(function () {
    var listProduct = JSON.parse(localStorage.getItem("listProductIdChoice"));

    if (listProduct) {
        listProductIdChoice = listProduct.listProductIdChoice;
    }

    console.log(listProductIdChoice);
    renderListProductChoice();
});

$(window).on("beforeunload", function () {
    // Xóa mục "listProductIdChoice" khỏi localStorage trước khi đóng trang
    localStorage.removeItem("listProductIdChoice");
});

async function renderListProductChoice() {
    for (var i = 0; i < listProductIdChoice.length; i++) {
        await $.ajax({
            type: "GET",
            url: "/cart-detail/" + listProductIdChoice[i],
            contentType: "application/json",
            success: function (response) {
                console.log("Lấy danh sách sản phẩm thành công!");

                var productName = "" + response.productName + "<br>" + "[" + response.colorName + "-" + response.sizeName + "]";
                var quantity = response.quantity;
                var price = response.discount === 0 ? response.price : response.price / 100 * (100 - response.discount);

                var priceFomat = formatPrice(price);

                // Tạo HTML string chứa <tr> và các <td>
                var rowHTML = "<tr>" +
                    "<td class='product'>" + productName + "</td>" +
                    "<td class='quantity'>" + quantity + "</td>" +
                    "<td class='price'>" + priceFomat + "</td>" +
                    "</tr>";

                // Thêm HTML string vào tbody
                $("#listProduct").append(rowHTML);

                var product = {
                    name: productName,
                    quantity: quantity,
                    price: price
                }

                listProduct.push(product);
                console.log(listProduct);
            },
            error: function (error) {
                console.error("Lỗi khi lấy danh sách sản phẩm:", error);
            }
        });
    }

    getTotalPrice();
}

function getTotalPrice(){
    var totalPriceProducts = 0;

    // Lặp qua mỗi sản phẩm trong listProduct và tính tổng giá trị
    for (var i = 0; i < listProduct.length; i++) {
        totalPriceProducts += listProduct[i].price * listProduct[i].quantity;
    }

    var formattedPrice = formatPrice(totalPriceProducts);

    // Hiển thị tổng giá trị đã được định dạng trên giao diện người dùng
    $("#totalPriceProducts").text(formattedPrice);
    $("#totalPrice").text(formattedPrice);
    console.log(totalPriceProducts);
}

function formatPrice(price) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
}