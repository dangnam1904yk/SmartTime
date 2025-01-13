document.addEventListener("DOMContentLoaded", function () {


    const bnt_add = document.getElementById("submit-add");
    const isActive = document.getElementById("isActive");
    const actionTableBodyView = document.getElementById("action-table-body-view");
    const actionTableBody = document.getElementById("action-table-body");

    const submitDataButton = document.getElementById("submit-data");
    const selectedDataForm = document.getElementById("selectedDataForm");
    const dataInput = document.getElementById("dataInput");
    const categorytimeLine = document.getElementById("categorytimeLine");
    const nameTimeSheet = document.getElementById("nameTimeSheet");
    const lessonTopic = document.getElementById("lessonTopic");
    const studentErrorCount = document.getElementById("studentErrorCount");
    const detailErrorStudent = document.getElementById("detailErrorStudent");
    const generalComment = document.getElementById("generalComment");
    const lessonEvaluation = document.getElementById("lessonEvaluation");
    const isTrain = document.getElementById("isTrain");
    const categoryTimeSheet = document.getElementById("categoryTimeSheet");

    let listTimeSheet = [];
    let listSoDauBai =[];

    categorytimeLine.addEventListener("change", function () {
        console.log(categorytimeLine.value);
        $.ajax({
            url: `/api/timeSheet/getTimeSheetByTimeLine?id=${categorytimeLine.value}`,
            method: 'GET',
            async: true, // Chờ phản hồi trước khi tiếp tục
            success: function (response) {
                console.log(response);
                if (response.length > 0) {
                    listTimeSheet = response;
                    for (let index = 0; index < response.length; index++) {
                        const element = response[index];
                        const newOption = document.createElement("option");
                        newOption.value = element.id;
                        newOption.textContent = mapperThuNgay(element.thu) + " - " + element.categoryPeriodName;
                        categoryTimeSheet.appendChild(newOption);
                    }
                }
            },
            error: function (error) {
                console.error('Error:', error);
            }
        });
    });

    $.ajax({
        url: `/api/timeSheet/getTimeSheetByTimeLine?id=${categorytimeLine.value}`,
        method: 'GET',
        async: false, // Chờ phản hồi trước khi tiếp tục
        success: function (response) {
            console.log(response);
            if (response.length > 0) {
                listTimeSheet = response;
                for (let index = 0; index < response.length; index++) {
                    const element = response[index];
                    const newOption = document.createElement("option");
                    newOption.value = element.id;

                    newOption.textContent = mapperThuNgay(element.thu) + " - " + element.categoryPeriodName;
                    categoryTimeSheet.appendChild(newOption);

                }
            }
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });

    function mapperThuNgay(input) {
        var labelContent = "";
        switch (true) {
            case (input === "T2"):
                labelContent = "Thứ 2"
                break;
            case (input === "T3"):
                labelContent = "Thứ 3"
                break;
            case (input === "T4"):
                labelContent = "Thứ 4"
                break;
            case (input === "T5"):
                labelContent = "Thứ 5"
                break;
            case (input === "T6"):
                labelContent = "Thứ 6"
                break;
            case (input === "T7"):
                labelContent = "Thứ 7"
                break;
            case (input === "CN"):
                labelContent = "Chủ nhật"
                break;
            default:
                labelContent = "Chủ nhật";
        }
        return labelContent;
    }


    categoryTimeSheet.addEventListener("change", function () {
        showDataByTimeSheetId(categoryTimeSheet.value);
    })

    function showDataByTimeSheetId(id) {
        var result = listTimeSheet.filter(item => item.id == id);
        console.log(result);
        if(result.length>0){
            addRowToTableView(result[0]);
        }
    
    }


    bnt_add.addEventListener("click", function () {
        var result = listTimeSheet.filter(item => item.id == categoryTimeSheet.value);
         var timeSheet = result[0]
       
        const data = {
            uid: GenerectUUID(),
            id: null,
            thu: timeSheet.thu,
            category_class_id: timeSheet.categoryClassId,
            category_period_id: timeSheet.categoryPeriodId,
            category_subject_id: timeSheet.categorySubjectId,
            category_room_id: timeSheet.categoryRoomId,
            user_id: timeSheet.userId,
            "timeSheetId": timeSheet.id,
            "categoryClassName": timeSheet.categoryClassName,
            "categoryPeriodName": timeSheet.categoryPeriodName,
            "categoryRoomName": timeSheet.categoryRoomName,
            "categorySubjectName": timeSheet.categorySubjectName,
            "labelThu": mapperThuNgay(timeSheet.thu),
            "userFullName": timeSheet.userFullName,
            "lessonTopic": lessonTopic.value,
            "studentErrorCount": studentErrorCount.value,
            "detailErrorStudent": detailErrorStudent.value,
            "generalComment": generalComment.value,
            "lessonEvaluation": lessonEvaluation.value,
            "isTrain": isTrain.checked,
            "daDay": isTrain.checked == true ? "X": ""
        }
        console.log(data);

        listSoDauBai.push(data)
        addRowToTable(data);
    });

    function addRowToTableView(data) {
        actionTableBodyView.innerHTML ="";
        const row = document.createElement("tr");

        // Tạo các ô dữ liệu cho hàng mới
        const cell4 = document.createElement('td');
        cell4.textContent = mapperThuNgay(data.thu);
        const cell5 = document.createElement('td');
        cell5.textContent = data.categoryPeriodName;
        const cell6 = document.createElement('td');
        cell6.textContent = data.categoryClassName;
        const cell7 = document.createElement('td');
        cell7.textContent = data.categoryRoomName;
        const cell8 = document.createElement('td');
        cell8.textContent = data.categorySubjectName;
        const cell9 = document.createElement('td');
        cell9.textContent = data.userFullName;
        
        // Thêm các ô vào hàng
        row.appendChild(cell4);
        row.appendChild(cell5);
        row.appendChild(cell6);
        row.appendChild(cell7);
        row.appendChild(cell8);
        row.appendChild(cell9);
        actionTableBodyView.appendChild(row);
    }

    function addRowToTable(data) {
        actionTableBodyView.innerHTML ="";
        const row = document.createElement("tr");

        // Tạo các ô dữ liệu cho hàng mới
        const cell0 = document.createElement('td');
        cell0.textContent = data.uid;
        cell0.style.display= "none";
        const cell00 = document.createElement('td');
        cell00.textContent = data.id;
        cell00.style.display= "none";
        
        const cell1 = document.createElement('td');
        cell1.textContent = mapperThuNgay(data.thu);
        const cell2 = document.createElement('td');
        cell2.textContent = data.categoryPeriodName;
        const cell3 = document.createElement('td');
        cell3.textContent = data.categoryClassName;
        const cell4 = document.createElement('td');
        cell4.textContent = data.categoryRoomName;
        const cell5 = document.createElement('td');
        cell5.textContent = data.categorySubjectName;
        const cell6 = document.createElement('td');
        cell6.textContent = data.userFullName;
        const cell7 = document.createElement('td');
        cell7.textContent = data.lessonTopic;
        const cell8 = document.createElement('td');
        cell8.textContent = data.studentErrorCount;
        const cell9 = document.createElement('td');
        cell9.textContent = data.detailErrorStudent;
        const cell10 = document.createElement('td');
        cell10.textContent = data.generalComment;
        const cell11 = document.createElement('td');
        cell11.textContent = data.lessonEvaluation;
        const cell12 = document.createElement('td');
        cell12.textContent = data.daDay;
        
        const cell13 = document.createElement('td');
        cell13.textContent = data.daDay;
        const button = document.createElement('button');
        button.textContent = 'Xóa';
        button.classList.add('remove-btn');
        cell13.appendChild(button);

        // Thêm các ô vào hàng
        row.appendChild(cell0);
        row.appendChild(cell00);
        row.appendChild(cell1);
        row.appendChild(cell2);
        row.appendChild(cell3);
        row.appendChild(cell4);
        row.appendChild(cell5);
        row.appendChild(cell6);
        row.appendChild(cell7);
        row.appendChild(cell8);
        row.appendChild(cell9);
        row.appendChild(cell10);
        row.appendChild(cell11);
        row.appendChild(cell12);
        row.appendChild(cell13);
        
        actionTableBody.appendChild(row);

        button.addEventListener('click', function () {
            // Tìm hàng chứa nút được nhấn
            const row = this.closest('tr');

            // Lấy tất cả giá trị trong hàng
            const cells = row.querySelectorAll('td');

            console.log(cells[0].textContent);
            const index = listSoDauBai.findIndex(item => item.uid === cells[0].textContent);

            if (index !== -1) {
                listTimeSheet.splice(index, 1);
                row.remove();
            }
        });
    }


    submitDataButton.addEventListener("click", function () {

        dataInput.value = JSON.stringify(listSoDauBai); // Gắn JSON vào input hidden
        selectedDataForm.submit(); // Gửi form

    });


    Notification.requestPermission().then(permission => {
        if (permission === "granted") {
            alert("Thông báo đã được kích hoạt!");
        } else {
            alert("Bạn đã từ chối thông báo.");
        }
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
