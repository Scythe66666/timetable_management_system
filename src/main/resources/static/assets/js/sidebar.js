console.log("sidebar js was included");

let sidebar = document.querySelector(".sidebar");
let closeBtn = document.querySelector("#btn");
// let searchBtn = document.querySelector(".bx-search");
let searchBtn = document.querySelector(".bxs-graduation");
let Class = document.querySelector("#Class");

closeBtn.addEventListener("click", () => {
  sidebar.classList.toggle("open");
  menuBtnChange();
});

searchBtn.addEventListener("click", () => {
  sidebar.classList.toggle("open");
  menuBtnChange();
});

function menuBtnChange() {
  if (sidebar.classList.contains("open")) {
    closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");
  } else {
    closeBtn.classList.replace("bx-menu-alt-right", "bx-menu");
  }
}



//dropdown button js
var selectElement = document.getElementById("Class");

  // Add change event listener to the select element
  selectElement.addEventListener("change", function() {
    // Get the selected option value
    var selectedOption = selectElement.options[selectElement.selectedIndex].value;
    console.log('selcted option value is ', selectedOption); 
    // If the selected option value is not empty, navigate to the URL
    if (selectedOption) {
      window.location.href = selectedOption;
    }
  });
