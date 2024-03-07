var sttCounter = 1;
var listProductCart = [];
var totalPrice = 0;
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
        var existingProduct = listProductCart.find(function(product) {
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
    var existingProductIndex = listProductCart.findIndex(function(product) {
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
    listProductCart = listProductCart.filter(function(product) {
        return product.id !== productDetailId;
    });

    getTotalPrice();
    console.log(listProductCart);
}

function getTotalPrice() {
    // Lặp qua từng sản phẩm trong listProductCart và tính tổng giá
    var totalPrice = listProductCart.reduce(function (accumulator, product) {
        return accumulator + (product.price * product.quantity);
    }, 0);

    console.log(totalPrice);

    // Định dạng số và hiển thị dấu phân cách hàng nghìn
    var formattedPrice = totalPrice.toFixed(0).replace(/\d(?=(\d{3})+$)/g, "$&.");
    $("#tong-cong-thanh-toan").text(formattedPrice);
}





