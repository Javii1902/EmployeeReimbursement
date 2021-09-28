function allRequest(){
    let url = 'http://localhost:7000/reimbursement/all';
    let xhr = new XMLHttpRequest();
    let total = 0;
    let highAmount = 0;
    let lowAmount = Number.MAX_VALUE;
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

                total = total + pend.amount;

                if(pend.amount > highAmount){
                    highAmount = pend.amount;
                }
                if(pend.amount < lowAmount){
                    lowAmount = pend.amount;
                }
                count = count+1;
            }

            let averageTotal = total/count;
            console.log("total = " + total);
            console.log("count = " + count);
            console.log("averageTotal = " + averageTotal);
            console.log("High amount = " + highAmount);
            console.log("Low amount = " + lowAmount);

            let stats = document.getElementById('statsTable');
            let newTotalTable = document.createElement('table');
            let statrow=document.createElement('tr');
            let totalAmount = document.createElement('th');
            let highReimAmount=document.createElement('th');
            let lowReimAmount=document.createElement('th');
            let averageAmount=document.createElement('th');
            totalAmount.innerText = "Total Spent";
            highReimAmount.innerText="Highest Amount";
            lowReimAmount.innerText="Lowest Amount";
            averageAmount.innerText="Average";
            totalAmount.id = "th";
            highReimAmount.id = "th";   
            lowReimAmount.id = "th";
            averageAmount.id = "th";

            newTotalTable.appendChild(statrow);
            statrow.appendChild(totalAmount);
            
            statrow.appendChild(highReimAmount);
            
            statrow.appendChild(lowReimAmount);
            statrow.appendChild(averageAmount);
            stats.appendChild(newTotalTable);

            let stats1 = document.getElementById('showStats');
            let newTotalTable1 = document.createElement('table');
            let statrow1=document.createElement('tr');
            let totalAmount1 = document.createElement("th");
            
            let highReimAmount1=document.createElement('th');
            
            let lowReimAmount1=document.createElement('th');
            let averageAmount1=document.createElement('th');
            totalAmount1.innerText = total;
            highReimAmount1.innerText=highAmount;
            lowReimAmount1.innerText=lowAmount;
            averageAmount1.innerText=averageTotal;

            totalAmount1.id = "tr";
            
            highReimAmount1.id = "tr";
    
            lowReimAmount1.id = "tr";
            averageAmount1.id = "tr";

            newTotalTable1.appendChild(statrow1);
            statrow1.appendChild(totalAmount1);
            statrow1.appendChild(highReimAmount1);
            statrow1.appendChild(lowReimAmount1);
            statrow1.appendChild(averageAmount1);
            stats1.appendChild(newTotalTable1);
    
        }
    }
    xhr.open('Get',url);
    xhr.send();
}

window.onload = allRequest();