//Show form
$(document).ready(function () {
    $('#showModalRole').click(function () {
        $('#roleModal').modal('show');
    });
});

function saveRole() {
    // Lấy dữ liệu từ biểu mẫu
    var name = $("#nameRole").val().trim();

    // Kiểm tra xem các trường có rỗng không
    if (name === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin!'
        });
        return;
    }

    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(name)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên chỉ được chứa chữ cái và khoảng trắng!'
        });
        return;
    }

    var url ="/admin/roles/create";
    var method = "POST";
    var dataToSend = {
            name: name,
        }

    // Gửi yêu cầu AJAX
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function(response) {
            console.log("Lưu thành công!");
            toastr.options = {
                            "closeButton": false,
                            "debug": false,
                            "newestOnTop": true,
                            "progressBar": true,
                            "positionClass": "toast-top-right",
                            "preventDuplicates": false,
                            "onclick": null,
                            "showDuration": "300",
                            "hideDuration": "1000",
                            "timeOut": "1000",
                            "extendedTimeOut": "1000",
                            "showEasing": "swing",
                            "hideEasing": "linear",
                            "showMethod": "fadeIn",
                            "hideMethod": "fadeOut"
                        };
            toastr.success("Thành công!", "", {
                    onHidden: function() { // Đoạn mã callback
                        // Đóng modal
                        $('#roleModal').modal('hide');
                        // Reload lại trang
                        location.reload();
                    }
                });
        },
        error: function(error) {
            console.error("Lỗi khi lưu:", error);
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": true,
                "progressBar": true,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "1000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
             toastr.success("Thất bại!");
        }
    });
}

