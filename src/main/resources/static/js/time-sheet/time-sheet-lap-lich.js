document.addEventListener("DOMContentLoaded", function () {

    const categoryUser = document.getElementById("categoryUser");
    const categoryPeriod = document.getElementById("categoryPeriod");
    const categorySubject = document.getElementById("categorySubject");
    const categoryClass = document.getElementById("categoryClass");
    const categoryRoom = document.getElementById("categoryRoom");
    const categoryThu = document.getElementById("categoryThu");
    const bnt_add = document.getElementById("submit-add");
    const isActive = document.getElementById("isActive");
    const actionTableBody = document.getElementById("action-table-body");
    const submitDataButton = document.getElementById("submit-data");
    const selectedDataForm = document.getElementById("selectedDataForm");
    const dataInput = document.getElementById("dataInput");
    const timeLine = document.getElementById("timeLine");
    const nameTimeSheet = document.getElementById("nameTimeSheet");
    const weekStart = document.getElementById("weekStart");
    const calendarType = document.getElementById("calendarType");
    const startDate = document.getElementById("startDate");
    const endDate = document.getElementById("endDate");
    


    const now = new Date();
    const year = now.getFullYear();
    const month = (now.getMonth() + 1).toString().padStart(2, '0'); // Thêm số 0 nếu tháng < 10

    // Đặt giá trị mặc định cho input
    const inputMonth = document.getElementById('thangPhanCong');

    const listTimeSheet = [];

    let labelUser = categoryUser.options[categoryUser.selectedIndex].text;
    let labelSubject = categorySubject.options[categorySubject.selectedIndex].text;
    let labelPeriod = categoryPeriod.options[categoryPeriod.selectedIndex].text;
    let labelClass = categoryClass.options[categoryClass.selectedIndex].text;
    let labelRoom = categoryRoom.options[categoryRoom.selectedIndex].text;
    let labelThu = categoryThu.options[categoryThu.selectedIndex].text;

    categoryUser.addEventListener("change", function () {
        labelUser = categoryUser.options[categoryUser.selectedIndex].text;
    });

    weekStart.addEventListener("change", function () {
        console.log(weekStart.value);
        
        if(weekStart.value ==""){
            weekStart.nextElementSibling.textContent = "Không được bỏ trống";
        }else{
            weekStart.nextElementSibling.textContent = "";   
        }
    });

    if(calendarType.value ==1){
        startDate.parentElement.parentElement.style.display ="none";
        endDate.parentElement.parentElement.style.display="none";
        weekStart.parentElement.parentElement.style.display="";
    }else{
        startDate.parentElement.parentElement.style.display ="";
        endDate.parentElement.parentElement.style.display="";
        weekStart.parentElement.parentElement.style.display="none";

    }

    calendarType.addEventListener("change", function () {
        if(calendarType.value ==1){
            startDate.parentElement.parentElement.style.display ="none";
            endDate.parentElement.parentElement.style.display="none";
            weekStart.parentElement.parentElement.style.display="";
        }else{
            startDate.parentElement.parentElement.style.display ="";
            endDate.parentElement.parentElement.style.display="";
            weekStart.parentElement.parentElement.style.display="none";

        }
    });

nameTimeSheet.addEventListener("change", function(){
    if(nameTimeSheet.value ==""){
        nameTimeSheet.nextElementSibling.textContent = "Không được bỏ trống";
    }else{
        nameTimeSheet.nextElementSibling.textContent = "";
    }
})

    categorySubject.addEventListener("change", function () {
        labelSubject = categorySubject.options[categorySubject.selectedIndex].text;
    });
    categoryPeriod.addEventListener("change", function () {
        labelPeriod = categoryPeriod.options[categoryPeriod.selectedIndex].text;
    });
    categoryClass.addEventListener("change", function () {
        labelClass = categoryClass.options[categoryClass.selectedIndex].text;
    });
    categoryRoom.addEventListener("change", function () {
        labelRoom = categoryRoom.options[categoryRoom.selectedIndex].text;
    });

    categoryThu.addEventListener("change", function () {
        labelThu = categoryThu.options[categoryThu.selectedIndex].text;
    });


    bnt_add.addEventListener("click", function () {
        if(calendarType.value =="1") {            
            if(weekStart.value ==""){
                weekStart.nextElementSibling.textContent = "Không được bỏ trống";
                return
            }else{
                weekStart.nextElementSibling.textContent = "";   
            }
        }

        if(nameTimeSheet.value ==""){
            nameTimeSheet.nextElementSibling.textContent = "Không được bỏ trống";
            return;
        }else{
            nameTimeSheet.nextElementSibling.textContent = "";
        }
        const data = {
            uid: GenerectUUID(),
            id: null,
            thu: categoryThu.value,
            categoryClassId: categoryClass.value,
            categoryPeriodId: categoryPeriod.value,
            categorySubjectId: categorySubject.value,
            categoryRoomId: categoryRoom.value,
            userId: categoryUser.value,
            "labelClass": labelClass,
            "labelPeriod": labelPeriod,
            "labelRoom": labelRoom,
            "labelSubject": labelSubject,
            "labelThu": labelThu,
            "labelUser": labelUser,
            "nameTimeSheet": nameTimeSheet.value
        }
        console.log(data);

        
        const index = listTimeSheet.findIndex(item => item.thu === data.thu && item.categoryPeriodId === data.categoryPeriodId) ;

            if (index !== -1) {
                Swal.fire({
                    title: "Trùng tiết đã phân công",
                    text: "",
                    icon: "warning" || 'info', // Các giá trị icon: 'success', 'error', 'warning', 'info', 'question'
                    timer: 5000, // Tự động đóng sau 5 giây
                    timerProgressBar: true,
                    showConfirmButton: false,
                    position: 'top-end', // Đặt vị trí thông báo
                    toast: true, // Hiển thị dạng "toast"
    
                });
                return;
            }
        
        listTimeSheet.push(data)
        addRowToTable(data);
    });

    function addRowToTable(data) {
        const row = document.createElement("tr");

        // Tạo các ô dữ liệu cho hàng mới
        const cell1 = document.createElement('td');
        cell1.textContent = data.uid;
        cell1.style.display = 'none'
        const cell2 = document.createElement('td');
        cell2.textContent = data.id;
        cell2.style.display = 'none'
        const cell3 = document.createElement('td');
        const button = document.createElement('button');
        button.textContent = 'Xóa';
        button.classList.add('remove-btn');
        cell3.appendChild(button);
        const cell5 = document.createElement('td');
        cell5.textContent = data.labelPeriod;
        const cell4 = document.createElement('td');
        cell4.textContent = data.labelThu;
        const cell6 = document.createElement('td');
        cell6.textContent = data.labelClass;
        const cell7 = document.createElement('td');
        cell7.textContent = data.labelRoom;
        const cell8 = document.createElement('td');
        cell8.textContent = data.labelSubject;
        const cell9 = document.createElement('td');
        cell9.textContent = data.labelUser;

        // Thêm các ô vào hàng
        row.appendChild(cell1);
        row.appendChild(cell2);
        row.appendChild(cell4);
        row.appendChild(cell5);
        row.appendChild(cell6);
        row.appendChild(cell7);
        row.appendChild(cell8);
        row.appendChild(cell9);
        row.appendChild(cell3);


        actionTableBody.appendChild(row);
        button.addEventListener('click', function () {
            // Tìm hàng chứa nút được nhấn
            const row = this.closest('tr');

            // Lấy tất cả giá trị trong hàng
            const cells = row.querySelectorAll('td');
            
            console.log(cells[0].textContent);
            const index = listTimeSheet.findIndex(item => item.uid === cells[0].textContent) ;

            if (index !== -1) {
                listTimeSheet.splice(index, 1);
                row.remove();
            }
        });
    }
    
    submitDataButton.addEventListener("click", function () {

        dataInput.value = JSON.stringify(listTimeSheet); // Gắn JSON vào input hidden
        const dataTimeLine = { isActive: isActive.checked,
                               "calendarType": calendarType.value, 
                               "weekStart": weekStart.value,
                               "startDate": startDate.value,
                               "endDate" : endDate.value,
                               "nameTimeLine": nameTimeSheet.value
                            }
        timeLine.value = JSON.stringify(dataTimeLine);
        selectedDataForm.submit(); // Gửi form

    });
    

    function GenerectUUID() {
        const list = ['A', 'b', 'c', '3', '6', 'D', 'e', '8', 'F', '9', 'T', '0'];
        const length = list.length;
        var random = "";
        for (let i = 0; i < 5; i++) {
            const randomIndex = Math.floor(Math.random() * length);
            random = random + (list[randomIndex]);
        }

        const date = new Date();
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-based
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');
        const milliseconds = String(date.getMilliseconds()).padStart(3, '0'); // Milliseconds
        const formattedDate = `${year}${month}${day}${hours}${minutes}${seconds}${milliseconds}${random}`;
        return formattedDate
    }
});
