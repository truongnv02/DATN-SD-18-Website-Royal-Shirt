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

function updateStaff(staffId) {
    // Lấy modal
    var modal = document.getElementById('showModalStaff');

    // Lấy thông tin của nhân viên từ server và điền vào modal
    var apiUrl = '/admin/staffs/findId/' + staffId;

    fetch(apiUrl)
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(function(data) {
            // Điền thông tin của nhân viên vào modal
            document.getElementById('name').value = data.name;
            document.getElementById('email').value = data.email;
            document.getElementById('phone').value = data.phone;
            document.getElementById('address').value = data.address;
            document.getElementById('role').value = data.roleId;

            // Hiển thị ảnh của nhân viên nếu có
            var avatarImg = document.getElementById('avatar');
            if (data.avatar) {
                avatarImg.src = 'data:image/jpeg;base64,' + data.avatar;
                avatarImg.style.display = 'block';
            } else {
                avatarImg.style.display = 'none';
            }

            // Hiển thị modal sau khi điền thông tin
            modal.style.display = 'block';
        })
        .catch(function(error) {
            console.error('Error updating staff:', error);
        });
}





