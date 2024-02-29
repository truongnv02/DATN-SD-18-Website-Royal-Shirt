//Show form
$(document).ready(function () {
    $('#showModalRole').click(function () {
        $('#roleModal').modal('show');
    });
    $('#closeModalRole').click(function () {
        $('#roleModal').modal('hide');
    });
});

function saveRole() {
    // Lấy dữ liệu từ biểu mẫu
    var name = $("#nameRole").val().trim();

    // Kiểm tra xem các trường có rỗng không
    if (name === "") {
        swal({
            title: "Lỗi!",
            text: "Vui lòng điền đầy đủ thông tin!",
            icon: "error"
        });
        return;
    }

    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(name)) {
        swal({
            title: "Lỗi!",
            text: "Tên chỉ được chứa chữ cái và khoảng trắng!",
            icon: "error"
        });
        return;
    }

    var url = "/admin/rest/roles/create";
    var method = "POST";
    var dataToSend = {
        name: name,
    };

    // Gửi yêu cầu AJAX
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function(response) {
            console.log("Lưu thành công!");
            Swal.fire({
                title: "Thành công!",
                text: "Lưu dữ liệu thành công!",
                icon: "success",
                didClose: function () {
                    location.reload();
                }
            });
        },
        error: function(error) {
            console.error("Lỗi khi lưu:", error);
            Swal.fire({
                title: "Thất bại!",
                text: "Đã xảy ra lỗi khi lưu dữ liệu!",
                icon: "error"
            });
        }
    });
}



