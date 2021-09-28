console.log("Login Page");

function login(){
    let url = 'http://localhost:7000/employee/'

    url = url + document.getElementById('employee_id');
    console.log(url);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readState === 4 && xhr.status === 200){
            let result = JSON.parse(xhr.response);
            console.log(employee_id);
        }
    }
    xhr.open('Post', url);
    xhr.send();
}
console.log()