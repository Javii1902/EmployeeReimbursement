function allRequest(){
    let url = 'http://localhost:7000/reimbursement/employeeid/reimbursements';
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let pends = JSON.parse(xhr.response);

            let pendinReq = document.getElementById('reimbursementsTable');
            let new_table = document.createElement('table');
            let row = document.createElement('tr');
            let rname = document.createElement('th');
            let amount = document.createElement('th');
            let status = document.createElement("th");
            let description = document.createElement('th');
            rname.innerText = "Reimbursement ID";
            amount.innerText = "Amount";
            status.innerText ="Status";
            description.innerText = "Description";

            rname.id = "th";
            amount.id = "th";
            status.id = "th";
            description.id="th";

            new_table.appendChild(row);
            row.appendChild(rname);
            row.appendChild(amount);
            row.appendChild(status);
            row.appendChild(description);
            pendinReq.appendChild(new_table);

            for (let pend of pends){
                let pendinReq = document.getElementById('reimbursementsTable');
                let new_table = document.createElement('table');
                let row = document.createElement('tr');
                let rname = document.createElement('th');
                let amount = document.createElement('th');
                let status = document.createElement("th");
                let description = document.createElement('th');
                rname.innerText = pend.reimbursement_id;
                amount.innerText = pend.amount.toFixed(2);
                status.innerText =pend.status;
                description.innerText = pend.description;
    
                rname.id = "tr";
                amount.id = "tr";
                status.id = "tr";
                description.id="tr";
    
                new_table.appendChild(row);
                row.appendChild(rname);
                row.appendChild(amount);
                row.appendChild(status);
                row.appendChild(description);
                pendinReq.appendChild(new_table);
            }
        }
    }
    xhr.open('Get',url);
    xhr.send();
}
window.onload = allRequest();