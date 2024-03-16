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