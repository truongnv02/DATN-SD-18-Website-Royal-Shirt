function readURL(input, thumbimage) {
    if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
        var reader = new FileReader();
        reader.onload = function (e) {
            $("#thumbimage").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
    else { // Sử dụng cho IE
        $("#thumbimage").attr('src', input.value);
    }
    $("#thumbimage").show();
    $('.filename').text($("#uploadfile").val());
    $('.Choicefile').css('background', '#14142B');
    $('.Choicefile').css('cursor', 'default');
    $(".removeimg").show();
    $(".Choicefile").unbind('click');

}
$(document).ready(function () {
    $(".Choicefile").bind('click', function () {
        $("#uploadfile").click();

    });
    $(".removeimg").click(function () {
        $("#thumbimage").attr('src', '').hide();
        $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
        $(".removeimg").hide();
        $(".Choicefile").bind('click', function () {
            $("#uploadfile").click();
        });
        $('.Choicefile').css('background', '#14142B');
        $('.Choicefile').css('cursor', 'pointer');
        $(".filename").text("");
    });
})

$(document).ready(function() {
    // Khởi tạo Select màu sắc và kích thước
    var selectColor = $('#select-colors').selectize({
        maxItems: null,
        valueField: 'id',
        labelField: 'title',
        searchField: 'title',
        create: false
    });

    var selectSize = $('#select-sizes').selectize({
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
        success: function(data) {
            // Tạo options màu sắc từ danh sách màu trong productListResponse
            var colorOptions = data.colors.map(function(color) {
                return { id: color.id, title: color.name };
            });

            // Tạo options kích thước từ kích thước trong productListResponse
            var sizeOptions = data.sizes.map(function(size) {
                return { id: size.id, title: size.name };
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
        error: function(err) {
            console.error('Error call API:', err);
        }
    });
});

