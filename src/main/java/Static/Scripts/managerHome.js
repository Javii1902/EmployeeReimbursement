function allRequest(){
    let url = 'http://localhost:7000/reimbursement/all';
    let xhr = new XMLHttpRequest();
    let total = 0;
    let highAmount = 0;
    let highUser = null;
    let lowAmount = Number.MAX_VALUE;
    let lowUser = null;
    let count = 0;

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

            employeeid.id = "th";
            rname.id = "th";
            amount.id = "th";
            status.id = "th";
            description.id="th";

            new_table.appendChild(row);
            row.appendChild(employeeid);
            row.appendChild(rname);
            row.appendChild(amount);
            row.appendChild(status);
            row.appendChild(description);
            pendinReq.appendChild(new_table);

            for (let pend of pends){
                let pendinReq = document.getElementById('reimbursementsTable');
                let new_table = document.createElement('table');
                let employeeid = document.createElement("th");
                let row = document.createElement('tr');
                let rname = document.createElement('th');
                let amount = document.createElement('th');
                let status = document.createElement("th");
                let description = document.createElement('th');
                employeeid.innerText = pend.employee_id;
                rname.innerText = pend.reimbursement_id;
                amount.innerText = pend.amount.toFixed(2);
                status.innerText =pend.status;
                description.innerText = pend.description;
    
                employeeid.id = "tr";
                rname.id = "tr";
                amount.id = "tr";
                status.id = "tr";
                description.id="tr";
    
                new_table.appendChild(row);
                row.appendChild(employeeid);
                row.appendChild(rname);
                row.appendChild(amount);
                row.appendChild(status);
                row.appendChild(description);
                pendinReq.appendChild(new_table);

                total = total + pend.amount;

                if(pend.amount > highAmount){
                    highAmount = pend.amount;
                    highUser = pend.employeeid;
                }
                if(pend.amount < lowAmount){
                    lowAmount = pend.amount;
                    lowUser = pend.employeeid;
                }
                count = count+1;
            }
            let averageTotal = total/count;
            console.log(total);
            console.log(count);
            console.log(averageTotal);

            let stats = document.getElementById('statsTable');
            let newTotalTable = document.createElement('table');
            let statrow=document.createElement('tr');
            let totalAmount = document.createElement('th');
            let highEmp=document.createElement('th');
            let highReimAmount=document.createElement('th');
            let lowEmp=document.createElement('th');
            let lowReimAmount=document.createElement('th');
            let averageAmount=document.createElement('th');
            totalAmount.innerText = "Total Spent";
            highEmp.innerText="Highest Spender";
            highReimAmount.innertext="Highest Amount";
            lowEmp.innerText="Lowest Spender";
            lowReimAmount.innerText="Lowest Amount";
            averageAmount.innerText="Average";

            totalAmount.id = "th";
            highEmp.id = "th";
            highReimAmount.id = "th";
            lowEmp.id = "th";
            lowReimAmount.id = "th";
            averageAmount.id = "th";

            newTotalTable.appendChild(statrow);
            statrow.appendChild(totalAmount);
            statrow.appendChild(highEmp);
            statrow.appendChild(highReimAmount);
            statrow.appendChild(lowEmp);
            statrow.appendChild(lowReimAmount);
            statrow.appendChild(averageAmount);
            stats.appendChild(newTotalTable);

            let stats1 = document.getElementById('statsTable');
            let newTotalTable1 = document.createElement('table');
            let statrow1=document.createElement('tr');
            let totalAmount1 = document.createElement('th');
            let highEmp1=document.createElement('th');
            let highReimAmount1=document.createElement('th');
            let lowEmp1=document.createElement('th');
            let lowReimAmount1=document.createElement('th');
            let averageAmount1=document.createElement('th');
            totalAmount1.innerText = total;
            highEmp1.innerText=highUser;
            highReimAmount1.innertext=highAmount;
            lowEmp1.innerText=lowUser;
            lowReimAmount1.innerText=lowAmount;
            averageAmount1.innerText=averageTotal;

            totalAmount1.id = "tr";
            highEmp1.id = "tr";
            highReimAmount1.id = "tr";
            lowEmp1.id = "tr";
            lowReimAmount1.id = "tr";
            averageAmount1.id = "tr";

            newTotalTable1.appendChild(statrow1);
            statrow.appendChild(totalAmount1);
            statrow.appendChild(highEmp1);
            statrow.appendChild(highReimAmount1);
            statrow.appendChild(lowEmp1);
            statrow.appendChild(lowReimAmount1);
            statrow.appendChild(averageAmount1);
            stats1.appendChild(newTotalTable1);
    
        }
    }
    xhr.open('Get',url);
    xhr.send();
}

window.onload = allRequest();