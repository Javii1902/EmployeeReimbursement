function getAllRecipes(){
    let url = 'http://localhost:7000/reimbursement/all';
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        
        if(xhr.readyState === 4 && xhr.status === 200){
            
            let reimbursements = JSON.parse(xhr.response);
            for(let reimbursement of reimbursements){
            let reimbursementContainer = document.getElementById('reimbursementContainer');
            let new_div = document.createElement('div');
            new_div.className = "reimbursementDiv";

            let new_h3 = document.createElement('h3');
            new_h3.innerText = employee.employeName;

            let new_para = document.createElement('p');
            new_para.innerText = employee.employeeDescription;

            let new_list = document.createElement('ol');

            let new_list_item = document.createElement('li');
            new_list_item.innerText = recipe.recipe_step;

            new_list.appendChild(new_list_item);
            new_div.appendChild(new_h3);
            new_div.appendChild(new_para);
            new_div.appendChild(new_list);

            // Now that we've created that div and all of its child elements, let's FINALLY append the new div we've created to the existing recipe_container on our web page.

            recipe_container.appendChild(new_div);
            }
        }
    }

    // Now let's open our HTTP request. We need to specify an HTTP verb and the URL.
    xhr.open('GET', url); //readyState is 1

    // Now let's send our HTTP request.
    xhr.send(); //readyState is 2
}

// Any function you call here is going to be invoked as soon as the window loads.
window.onload = function(){
    this.getAllRecipes();
}
