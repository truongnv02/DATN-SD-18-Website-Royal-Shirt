var orderTabId = 0;


//Counter page
function addCounterPage() {
    if (orderTabId < 5) {
        orderTabId++;
        renderButtonOrderTab(orderTabId);
        renderCounterPage(orderTabId);
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tối đa 5 hóa đơn!'
        });
        return;
    }
}

function renderButtonOrderTab(orderTabId) {
    var buttonOrderTab = "<button class=\"btn btn-add-order\" data-orderTabId='" + orderTabId + "'>Hóa đơn " + orderTabId + "</button>";

    $("#listCounter").append(buttonOrderTab);

    // Khi click vào button
    $('.btn-add-order').click(function () {
        // Xóa class 'btn-active' từ tất cả các button
        $('.btn-add-order').removeClass('btn-active');

        // Thêm class 'btn-active' cho button được click
        $(this).addClass('btn-active');
    });

    // Kích hoạt sự kiện click cho nút mới thêm vào
    $('.btn-add-order[data-orderTabId="' + orderTabId + '"]').click();
}

function renderCounterPage(orderTabId){
    var counterPage = "";
}