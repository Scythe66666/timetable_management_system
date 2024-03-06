var days= ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday" , "Saturday", "Sunday"];
var i = 0;
var list = document.querySelectorAll(".days");
console.log("the elements of the list are "+ list);
list.forEach(function(element){
    element.textContent = days[i++];
});

