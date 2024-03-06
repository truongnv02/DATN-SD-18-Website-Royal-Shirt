var sttCounter = 1;

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
    var existingRow = $("#cartTable").find("tr[data-product-id='" + productDetailId + "']");

    if (existingRow.length > 0) {
        // Tăng số lượng nếu đã có trong giỏ hàng
        var quantity = parseInt(existingRow.find(".so_luong input").val()) + 1;
        existingRow.find(".so_luong input").val(quantity);
    } else {
        var quantity = 1;
        // Thêm dòng mới vào bảng giỏ hàng
        var newRow = "<tr data-product-id='" + productDetailId + "'>" +
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
    }
}

function removeFromCart(button) {
    $(button).closest("tr").remove();
}
