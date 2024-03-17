var listProductChoice = [];

$(document).ready(function () {
    var listProduct = JSON.parse(localStorage.getItem("listProductChoice"));

    if (listProduct) {
        listProductChoice = listProduct.listProductChoice;
    }

    console.log(listProductChoice);
});

$(window).on("beforeunload", function() {
    // Xóa mục "listProductChoice" khỏi localStorage trước khi đóng trang
    localStorage.removeItem("listProductChoice");
});

async function renderListProductChoice(){
    for (var i = 0; i < listProductChoice.length; i++) {
        await $.ajax({
            type: "GET",
            url: "/cart-detail/" + listProductChoice[i],
            contentType: "application/json",
            success: function (response) {
                console.log("Lấy danh sách sản phẩm thành công!");

            },
            error: function (error) {
                console.error("Lỗi khi lấy danh sách sản phẩm:", error);
            }
        });
    }
}