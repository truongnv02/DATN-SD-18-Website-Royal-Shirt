$(document).ready(function() {
    $('.cart-checkbox').change(function() {
        calculateTotal();
    });

    function calculateTotal() {
        var selectedIds = [];
        $('.cart-checkbox:checked').each(function() {
            selectedIds.push($(this).val());
        });

        $.ajax({
            type: 'POST',
            url: '/sumPrice',
            contentType: 'application/json',
            data: JSON.stringify(selectedIds),
            success: function(response) {
                var formattedTongTien = formatCurrency(response.totalPrice);
                $('#sumPrice').text(formattedTongTien);
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }

    function formatCurrency(amount) {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
    }
});
