
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("menu-toggle").addEventListener("click", function (e) {
        e.preventDefault();
        document.getElementById("wrapper").classList.toggle("menuDisplayed");
    });
});