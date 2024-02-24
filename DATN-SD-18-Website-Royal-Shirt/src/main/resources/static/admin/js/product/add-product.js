var selectColor, selectSize;

$(document).ready(function () {
    // Khởi tạo Select màu sắc và kích thước
    selectColor = $('#select-colors').selectize({
        maxItems: null,
        valueField: 'id',
        labelField: 'title',
        searchField: 'title',
        create: false
    });

    selectSize = $('#select-sizes').selectize({
        maxItems: null,
        valueField: 'id',
        labelField: 'title',
        searchField: 'title',
        create: false
    });

    // Gọi API để lấy thông tin từ server
    $.ajax({
        url: '/admin/rest/add-product',
        type: 'GET',
        success: function (data) {
            // Tạo options màu sắc từ danh sách màu trong productListResponse
            var colorOptions = data.colors.map(function (color) {
                return {id: color.id, title: color.name};
            });

            // Tạo options kích thước từ kích thước trong productListResponse
            var sizeOptions = data.sizes.map(function (size) {
                return {id: size.id, title: size.name};
            });

            // Cập nhật options của màu sắc
            selectColor[0].selectize.clearOptions();
            selectColor[0].selectize.addOption(colorOptions);
            selectColor[0].selectize.refreshItems();

            // Cập nhật options của kích thước
            selectSize[0].selectize.clearOptions();
            selectSize[0].selectize.addOption(sizeOptions);
            selectSize[0].selectize.refreshItems();
        },
        error: function (err) {
            console.error('Error call API:', err);
        }
    });

});

// Hàm để lấy danh sách sản phẩm chi tiết từ các mục đã chọn từ Selectize dropdowns
function showListProductDetail() {
    // Lấy thông tin chi tiết về các mục đã chọn từ dropdown màu sắc
    var selectedColorItems = selectColor[0].selectize.items.map(function (item) {
        var option = selectColor[0].selectize.getItem(item);
        return {id: item, name: option.text()};
    });

    // Lấy thông tin chi tiết về các mục đã chọn từ dropdown kích thước
    var selectedSizeItems = selectSize[0].selectize.items.map(function (item) {
        var option = selectSize[0].selectize.getItem(item);
        return {id: item, name: option.text()};
    });

    var listProductDetail = $("#listProductDetail");

    // Xóa tất cả các phần tử trong tbody
    listProductDetail.empty();

    // Biến đếm STT
    var sttCounter = 1;

    // Lấy giá trị của ô nhập liệu Tên sản phẩm
    var productName = $("#input-product-name").val();

    // Gọi hàm khác hoặc thực hiện các xử lý khác với listColor và listSize ở đây
    for (var i = 0; i < selectedColorItems.length; i++) {
        for (var j = 0; j < selectedSizeItems.length; j++) {

            var productDetailName = productName + ' [' +
                '<strong>' + selectedColorItems[i].name + '</strong>' + ' - ' +
                '<strong>' + selectedSizeItems[j].name + '</strong>' + ']';

            var productDetail = $(
                '<tr>\n' +
                '    <td class="stt">' + sttCounter + '</td>\n' +
                '    <td class="ten_danh_muc">' + productDetailName + '</td>\n' +
                '    <td class="so_luong"><input type="number" value="10" min="0"></td>\n' +
                '    <td class="gia_ban" id="gia_ban"><input type="number" data-raw-value="1000000" min="0"></td>\n' +
                '    <td class="table-td-center tinh_nang">\n' +
                '        <button class="btn btn-primary btn-sm trash" id="removeProductDetail" type="button" title="Xóa">' +
                '            <i\n class="fas fa-trash-alt"></i>\n' +
                '        </button>\n' +
                '    </td>' +
                '</tr>'
            );
            $(productDetail).appendTo("#listProductDetail");

            // Tăng biến đếm STT
            sttCounter++;

            // Thêm sự kiện click cho nút xóa
            $(productDetail).find('.trash').click(function () {
                $(this).closest('tr').remove(); // Xóa hàng chứa nút xóa được click
                updateSTT(); // Cập nhật lại giá trị STT
            });
        }
    }
}



// Hàm để cập nhật lại giá trị STT cho tất cả các hàng trong bảng
function updateSTT() {
    $('#listProductDetail tr').each(function (index, row) {
        $(row).find('.stt').text(index + 1);
    });
}

function readURL(input) {
    if (input.files && input.files.length > 0) {
        for (var i = 0; i < input.files.length; i++) {
            var reader = new FileReader();

            reader.onload = function (e) {
                // Tạo một container cho ảnh và nút xóa
                var imageContainer = $(
                    '<div class="image-product-container">' +
                    '   <img src="' + e.target.result + '" alt="Thumb image" class="thumbimage"/>' +
                    '   <a class="removeimg" href="javascript:" style="display: inline"></a>' +
                    '</div>'
                );

                // Thêm container vào thumbbox
                $("#thumbbox").append(imageContainer);

                // Sự kiện click cho nút xóa
                imageContainer.find(".removeimg").on("click", function () {
                    $(this).closest(".image-product-container").remove();
                    $("#myfileupload").html('<input type="file" id="uploadfile" name="ImageUpload" multiple onchange="readURL(this)"/>');
                    $('.Choicefile').css('background', '#14142B');
                    $('.Choicefile').css('cursor', 'pointer');
                    $(".filename").text("");
                });
            };

            reader.readAsDataURL(input.files[i]);
        }
    }

    // Hiển thị các phần tử khi có ảnh
    $(".Choicefile").css('background', '#14142B');
    $(".Choicefile").css('cursor', 'default');
}

$(document).ready(function () {
    $(".Choicefile").bind('click', function () {
        $("#uploadfile").click();

    });
})



