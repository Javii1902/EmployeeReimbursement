function getAllEmployees(){
    let url = 'http://localhost:7000/employees/all';

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let recipes = json.parse(xhr.response);
            console.log(employees);

        }
    }
    xhr.open('GET',url);
    xhr.send();
}
window.onload = function (){
    this.getAllEmployees();
}