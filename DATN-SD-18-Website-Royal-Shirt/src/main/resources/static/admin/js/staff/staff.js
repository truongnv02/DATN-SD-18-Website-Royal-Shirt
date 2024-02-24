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

function saveStaff() {
    // Lấy dữ liệu từ các trường nhập liệu
    var name = document.getElementById('name').value;
    var email = document.getElementById('email').value;
    var phone = document.getElementById('phone').value;
    var address = document.getElementById('address').value;
    var password = document.getElementById('password').value;
    var role = document.getElementById('role').value;
    var image = document.getElementById('uploadfile').files[0]; // lấy tệp ảnh được chọn

    // Tạo FormData object để gửi dữ liệu và tệp ảnh lên server
    var formData = new FormData();
    formData.append('name', name);
    formData.append('email', email);
    formData.append('phone', phone);
    formData.append('address', address);
    formData.append('password', password);
    formData.append('role', role);
    formData.append('image', image);

    // Gửi yêu cầu AJAX
    $.ajax({
        url: '/admin/staffs/create',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
          alert('File uploaded successfully!');
        },
        error: function(jqXHR, textStatus, errorThrown) {
          alert('An error occurred while uploading the file: ' + errorThrown);
        }
      });
}

function setStatusStaff(button) {
    var staffId = button.getAttribute("data-id");

    Swal.fire({
        title: "Xác nhận",
        text: "Bạn có chắc chắn muốn thay đổi trạng thái của nhân viên này?",
        icon: "warning",
        showCancelButton: true, // Hiển thị nút Hủy
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Đồng ý",
        cancelButtonText: "Hủy"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                url: "/admin/rest/staffs/setStatus/" + staffId,
                success: function (response) {
                    Swal.fire({
                        title: "Thành công!",
                        text: "Thay đổi trạng thái thành công!",
                        icon: "success",
                    }).then(() => {
                        // Reload trang sau khi thành công
                        location.reload();
                    });
                },
                error: function () {
                    Swal.fire({
                        title: "Lỗi!",
                        text: "Đã xảy ra lỗi khi thay đổi trạng thái!",
                        icon: "error",
                    });
                }
            });
        }
    });
}
















