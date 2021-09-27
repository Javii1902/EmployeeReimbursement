console.log("Login Page");

function login(){
    let url = 'http://localhost:7000/employee/'

    url = url + document.getElementById('employee_id').value;
    console.log(url);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readState === 4 && xhr.status === 200){
            let result = JSON.parse(xhr.response);
            localStorage.setItem("employee_id",result.employee_id);
            console.log(employee_id);
        }
    }
    xhr.open('GET', url);
    xhr.send();
}

function loginCookie(){
    let url = 'http://localhost:7000/employee/'

    url = url + document.getElementById('employee_id').value;
    console.log(url);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readState === 4 && xhr.status === 200){
            let result = JSON.parse(xhr.response);
            document.cookie = "employee_id = " + result.employee_id; 
            console.log(employee_id);
        }
    }
    xhr.open('GET', url);
    xhr.send();
}