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

    const now = new Date();
    const year = now.getFullYear();
    const month = (now.getMonth() + 1).toString().padStart(2, '0'); // Thêm số 0 nếu tháng < 10

    // Đặt giá trị mặc định cho input
    const inputMonth = document.getElementById('thangPhanCong');
      inputMonth.value = `${year}-${month}`;


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
        const data = {
            uid: GenerectUUID(),
            id: null,
            thu: categoryThu.value,
            category_class_id: categoryClass.value,
            category_period_id: categoryPeriod.value,
            category_subject_id: categorySubject.value,
            category_room_id: categoryRoom.value,
            user_id: categoryUser.value,
            "labelClass": labelClass,
            "labelPeriod": labelPeriod,
            "labelRoom": labelRoom,
            "labelSubject": labelSubject,
            "labelThu": labelThu,
            "labelUser": labelUser,
            "nameTimeSheet": nameTimeSheet.value
        }
        console.log(data);
        
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
        const cell4 = document.createElement('td');
        cell4.textContent = data.labelPeriod;
        const cell5 = document.createElement('td');
        cell5.textContent = data.labelThu;
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
        const dataTimeLine = { isActive: isActive.checked, month: inputMonth.value }
        timeLine.value = JSON.stringify(dataTimeLine);
        selectedDataForm.submit(); // Gửi form

    });
    
   
        // Notification.requestPermission().then(permission => {
        //     if (permission === "granted") {
        //         alert("Thông báo đã được kích hoạt!");
        //     } else {
        //         alert("Bạn đã từ chối thông báo.");
        //     }
        // });

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
