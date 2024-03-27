console.log("add lecture js file was added to the page ");
var list_buttons = document.querySelectorAll("table .non-Empty");
var list_td = document.querySelectorAll("td:not(.days)");
list = getParentList(getParentList(list_buttons));
list = removeDuplicates(list);
list_empty_td = subtractItems(Array.from(list_td), Array.from(list));
list_empty_td.forEach(
    function(item)
    {
        item.style.backgroundColor = "green";
    }
);
list_empty_td = getChildList(list_empty_td);









console.log("list of divs is ", list);
console.log("list of td's is ", list_td);
function getChildList(list)
{
    var parentList = [];
    list.forEach(
        function(button)
        {
            parentList.push(button.querySelector('a'));
        }
    )
    return parentList;
}
function getParentList(list)
{
    var parentList = [];
    list.forEach(
        function(button)
        {
            parentList.push(button.parentElement);
        }
    )
    return parentList;
}

function removeDuplicates(list)
{
    return [...new Set(list)];
}

function subtractItems(array1, array2) {
    return array1.filter(item => !array2.includes(item));
}

function deactivateAllLinks(list)
{
    list.forEach(
        function(item)
        {
            item.href= "javascript:void(0)";
        }
    );
}