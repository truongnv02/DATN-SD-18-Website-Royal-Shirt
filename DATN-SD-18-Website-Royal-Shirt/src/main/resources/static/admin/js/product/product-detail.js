//Show form
$(document).ready(function () {
    $('#showFormProductDetail').click(function () {
        $('#UpdateProductDetailModal').modal('show');
    });
    $('#closeFormProductDetail').click(function () {
        $('#UpdateProductDetailModal').modal('hide');
    });

    var productId = $('#product-id').attr("data-product-id");

    getListURL(productId);

});

function getListURL(productId){
    listUrlImage = [];
    $.ajax({
        type: "GET",
        url: "/admin/rest/image/findByProductId/" + productId,
        success: function (response){
            console.log("Lấy ảnh thành công!");
            for (var i = 0; i < response.length; i++) {
                listUrlImage.push(response[i].urlImage);
            }
            // Gọi hàm writeURL để hiển thị ảnh
            writeURL();
        },
        error: function (error) {
            console.log('Error fetching image data:', error);
            // Xử lý lỗi nếu cần
        }
    })
}

function writeURL() {
    // Lặp qua mảng listUrlImage và hiển thị từng ảnh
    listUrlImage.forEach(function (url) {
        var imageContainer = $(
            '<div class="image-product-container">' +
            '   <img src="' + url + '" alt="Thumb image" class="thumbimage"/>' +
            '   <a class="removeimg" href="javascript:" style="display: inline"></a>' +
            '</div>'
        );

        // Thêm container vào thumbbox
        $("#thumbbox").append(imageContainer);

        // Sự kiện click cho nút xóa
        imageContainer.find(".removeimg").on("click", function () {
            var removedImage = $(this).closest(".image-product-container").find("img").attr("src");
            // Xóa ảnh khỏi biến listUrlImage
            listUrlImage = listUrlImage.filter(function (img) {
                return img !== removedImage;
            });
            $(this).closest(".image-product-container").remove();
            console.log(listUrlImage);
            $("#myfileupload").html('<input type="file" id="uploadfile" name="ImageUpload" multiple onchange="readURL(this)"/>');
            $('.Choicefile').css('background', '#14142B');
        });
    });
    console.log('writeURL');
}

function updateProductDetailForm(element){
    var productDetailId = element.getAttribute("data-product-detail-id")
    var productDetailColor = element.getAttribute("data-product-detail-color")
    var productDetailSize = element.getAttribute("data-product-detail-size")

    // Thêm thuộc tính productDetailId vào modal để update
    $('#ProductDetailModal').attr('product-detail-id-update', productDetailId);

    // Thực hiện AJAX request để lấy dữ sản phẩm mục từ backend
    $.ajax({
        type: 'GET',
        url: '/admin/rest/product-detail/formUpdate/' + productDetailId,
        success: function (productDetail) {
            // Hiển thị hộp thoại modal
            $('#ProductDetailModal').modal('show');

            // Điền dữ liệu vào các trường biểu mẫu
            $('#product-detail-color').text("Màu sắc: " + productDetailColor);
            $('#product-detail-size').text("Kích thước: " + productDetailSize);
            $('#product-detail-weight').val(productDetail.weight);
            $('#product-detail-quantity').val(productDetail.quantity);
            $('#product-detail-price').val(productDetail.price);
        },
        error: function (error) {
            console.log('Error fetching productDetail data:', error);
            // Xử lý lỗi nếu cần
        }
    });
}

function save() {
    var productDetailId = $('#ProductDetailModal').attr("product-detail-id-update");
    var productDetailWeight = $('#product-detail-weight').val();
    var productDetailQuantity = $("#product-detail-quantity").val();
    var productDetailPrice = $("#product-detail-price").val();
    var productDetailStatus = 0;

    var dataToSend = {
        id: productDetailId,
        weight: productDetailWeight,
        quantity: productDetailQuantity,
        price: productDetailPrice,
        status: productDetailStatus
    }

    // Gửi yêu cầu AJAX
    $.ajax({
        type: "PUT",
        url: "/admin/rest/product-detail/update/" + productDetailId,
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function (response) {
            console.log("Lưu Biến Thể thành công!");

            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Lưu Biến Thể thành công!',
                didClose: function () {
                    location.reload();
                }
            });
        },
        error: function (error) {
            console.error("Lỗi khi lưu Biến Thể:", error);

            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi lưu Biến Thể!'
            });
        }
    });
}