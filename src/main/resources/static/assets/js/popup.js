
var popup = document.getElementById("popup");
var popup2 = document.getElementById("popup2");

function openPopup() {
    console.log("open popu was called");
    popup.classList.add("open-popup");
}
function closePopup() {
    popup.classList.remove("open-popup");
}

function closePopup2() {
    popup2.classList.remove("open-popup");
}
popup2.classList.add("open-popup");
console.log("js file executed succesfully")