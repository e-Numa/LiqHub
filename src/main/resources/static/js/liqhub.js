const menu = document.querySelector('#menu-bar');
const navbar = document.querySelector('.navbar');
const icons = document.querySelectorAll('.icons');
const header2 = document.querySelector('.secondHeader');
const hamburger = document.querySelector('.fa-bars');
const closed = document.querySelector('.fa-window-close');

menu.addEventListener('click', function () {
    navbar.classList.toggle('shownav');
    closed.classList.toggle('show');
    hamburger.classList.toggle('hide');
    // navbar.animate(keyframes)
})


let countDownDate = new Date("November 1, 2023 16:00:00").getTime();

let myTimer = setInterval(function () {
    let now = new Date().getTime();
    let timeLeft = countDownDate - now;

    let days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
    let hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
    let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

    document.getElementById("day").innerHTML = days + "d "
    document.getElementById("hour").innerHTML = hours + "h "
    document.getElementById("minute").innerHTML = minutes + "m "
    document.getElementById("second").innerHTML = seconds + "s"
}, 1000)

window.onscroll = function () { mySticky() };

let sticky = header2.offsetTop;

function mySticky() {
    if (window.scrollY >= sticky) {
        header2.classList.add("sticky")
    } else {
        header2.classList.remove("sticky");
    }
}