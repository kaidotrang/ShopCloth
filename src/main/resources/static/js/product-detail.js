document.addEventListener('DOMContentLoaded', function () {
    const mainImage = document.getElementById('mainImage');
    const thumbnails = document.querySelectorAll('.thumbnail');

    thumbnails.forEach(function (thumbnail) {
        thumbnail.addEventListener('click', function () {
            const imageUrl = this.getAttribute('data-image-url');
            mainImage.setAttribute('src', imageUrl);
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const variantSelect = document.getElementById('variant');
    const quantityInput = document.getElementById('quantity');
    const priceContainer = document.getElementById('variant-price');
    const priceValue = document.getElementById('price-value');

    function updatePrice() {
        const selectedOption = variantSelect.options[variantSelect.selectedIndex];
        const price = parseFloat(selectedOption.getAttribute('data-price'));
        const quantity = parseInt(quantityInput.value) || 1;

        if (!isNaN(price)) {
            const total = price * quantity;
            priceValue.textContent = new Intl.NumberFormat('vi-VN').format(total) + ' VNĐ';
            priceContainer.style.display = 'block';
        } else {
            priceValue.textContent = '';
            priceContainer.style.display = 'none';
        }
    }

    variantSelect.addEventListener('change', updatePrice);
    quantityInput.addEventListener('input', updatePrice);
});

document.querySelector('.buy-now').addEventListener('click', function(event) {
    const variantSelect = document.getElementById('variant');
    const selectedVariantId = variantSelect.value; // Lấy giá trị của variant đã chọn

    if (selectedVariantId === "") {
        alert("Vui lòng chọn phiên bản sản phẩm");
        event.preventDefault(); // Ngừng hành động nếu chưa chọn variant
    } else {
        const form = document.querySelector('.variant-form'); // Form của sản phẩm
        form.action = '/order/placeFromProduct/' + selectedVariantId; // Cập nhật action của form
    }
});
