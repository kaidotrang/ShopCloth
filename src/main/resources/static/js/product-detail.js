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
