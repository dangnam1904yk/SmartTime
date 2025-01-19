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

    const SO_DAU_BAI = 1;
    let listTimeSheet = [];
    let listSoDauBai = [];
    let listTimeLine = [];
    let uidEdit = "";

    categorytimeLine.addEventListener("change", function () {
        console.log(categorytimeLine.value);
        categoryTimeSheet.innerHTML = "";
        $.ajax({
            url: `/api/timeSheet/getTimeSheetByTimeLine?id=${categorytimeLine.value}`,
            method: 'GET',
            async: true, // Chờ phản hồi trước khi tiếp tục
            success: function (response) {
                console.log(response);
                actionTableBody.innerHTML = "";
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

                showDataByTimeSheetId(response[0].id);
            }
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });


    $.ajax({
        url: `/api/timeLine/get-by-type?type=${SO_DAU_BAI}`,
        method: 'GET',
        async: true, // Chờ phản hồi trước khi tiếp tục
        success: function (response) {
            console.log(response);
            if (response.length > 0) {
                listTimeLine = response;

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

    lessonTopic.addEventListener("change", function () {
        if (lessonTopic.value == "") {
            lessonTopic.nextElementSibling.textContent = "Không được bỏ trống";
        } else {
            lessonTopic.nextElementSibling.textContent = "";
        }
    })

    lessonEvaluation.addEventListener("change", function () {
        if (lessonEvaluation.value == "") {
            lessonEvaluation.nextElementSibling.textContent = "Không được bỏ trống";
        } else {
            lessonEvaluation.nextElementSibling.textContent = "";
        }
        if(lessonEvaluation.value <0 || lessonEvaluation.value >10){
            lessonEvaluation.nextElementSibling.textContent ="Chỉ nhập điểm từ 0 - 10";
        }else{
            lessonEvaluation.nextElementSibling.textContent = "";
        }
    })

    studentErrorCount.addEventListener("change", function(){
        if(studentErrorCount.value <0 ){
            studentErrorCount.nextElementSibling.textContent ="Chỉ được phép nhập số nguyên dương"
        }
        if(studentErrorCount.value.includes(",") || studentErrorCount.value.includes(".") ){
            studentErrorCount.nextElementSibling.textContent ="Chỉ được phép nhập số nguyên dương"
        }else{
            studentErrorCount.nextElementSibling.textContent ="";
        }
    })


    function showDataByTimeSheetId(id) {
        var result = listTimeSheet.filter(item => item.id == id);
        if (result.length > 0) {
            addRowToTableView(result[0]);
        }

    }


    bnt_add.addEventListener("click", function () {
        var result = listTimeSheet.filter(item => item.id == categoryTimeSheet.value);

        var timeSheet = result[0]
        if (lessonTopic.value == "") {
            lessonTopic.nextElementSibling.textContent = "Không được bỏ trống";
            return;
        }
        if(studentErrorCount.value <0 ){
            studentErrorCount.nextElementSibling.textContent ="Chỉ được phép nhập số nguyên dương"
            return;
        }
        
        if (lessonEvaluation.value == "") {
            lessonEvaluation.nextElementSibling.textContent = "Không được bỏ trống";
            return;
        }
        if(lessonEvaluation.value <0 || lessonEvaluation.value >10){
            lessonEvaluation.nextElementSibling.textContent ="Chỉ nhập điểm từ 0 - 10";
            return;
        }
        if(lessonEvaluation.value.includes(",")){
            lessonEvaluation.value = lessonEvaluation.value.replace(",", ".");
        }
        const data = {
            "uid": GenerectUUID(),
            "id": null,
            "thu": timeSheet.thu,
            "categoryClassId": timeSheet.categoryClassId,
            "categoryPeriodId": timeSheet.categoryPeriodId,
            "categorySubjectId": timeSheet.categorySubjectId,
            "categoryRoomId": timeSheet.categoryRoomId,
            "userId": timeSheet.userId,
            "timeSheetId": timeSheet.id,
            "categoryClassName": timeSheet.categoryClassName,
            "categoryPeriodName": timeSheet.categoryPeriodName,
            "categoryRoomName": timeSheet.categoryRoomName,
            "categorySubjectName": timeSheet.categorySubjectName,
            "labelThu": mapperThuNgay(timeSheet.thu),
            "userFullName": timeSheet.userFullName,
            "lessonTopic": lessonTopic.value,
            "studentErrorCount": parseInt(studentErrorCount.value),
            "detailErrorStudent": detailErrorStudent.value,
            "generalComment": generalComment.value,
            "lessonEvaluation": lessonEvaluation.value,
            "isTrain": isTrain.checked,
            "daDay": isTrain.checked == true ? "X" : ""
        }

        const index = listSoDauBai.findIndex(item => item.thu === data.thu && item.categoryPeriodId === data.categoryPeriodId && item.uid !== uidEdit);

        if (index !== -1) {
            Swal.fire({
                title: "Trùng tiết",
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
        var tr_row = actionTableBody.querySelectorAll("tr");
        if (uidEdit !== "") {
            for (let index = 0; index < tr_row.length; index++) {
                const element = tr_row[index];
                if(element.childNodes[0].textContent === uidEdit){
                    element.remove();
                    break;
                }
            }
        }

        let dataIndex = listSoDauBai.findIndex((item => item.uid === uidEdit))
        if (dataIndex !== -1) {
            listSoDauBai.splice(dataIndex, 1);
        }
        listSoDauBai.unshift(data)
        addRowToTable(data, "fisrt");
        lessonTopic.value ="";
        lessonEvaluation.value="";
        studentErrorCount.value="";
        generalComment.value="";
        detailErrorStudent.value="";
    });

    function addRowToTableView(data) {
        actionTableBodyView.innerHTML = "";
        const row = document.createElement("tr");

        // Tạo các ô dữ liệu cho hàng mới
        const cell4 = document.createElement('td');
        cell4.textContent = data.thu.length > 3 ? data.thu : mapperThuNgay(data.thu);
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

    function addRowToTable(data, positionAdd) {
        actionTableBodyView.innerHTML = "";
        const row = document.createElement("tr");

        // Tạo các ô dữ liệu cho hàng mới
        const cell0 = document.createElement('td');
        cell0.textContent = data.uid;
        cell0.style.display = "none";
        const cell00 = document.createElement('td');
        cell00.textContent = data.id;
        cell00.style.display = "none";

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
        cell12.textContent = data.isTrain === true ? "Đã dạy" : "Chưa dạy";

        const cell13 = document.createElement('td');

        const buttonDelete = document.createElement('button');
        buttonDelete.textContent = 'Xóa';
        buttonDelete.classList.add('btn');
        buttonDelete.classList.add('btn-danger');
        buttonDelete.classList.add('remove-btn');


        const buttonEdit = document.createElement('button');
        buttonEdit.textContent = 'Sửa';
        buttonEdit.classList.add('edit-btn');
        buttonEdit.classList.add('btn');
        buttonEdit.classList.add('btn-success');
        buttonEdit.style.marginRight = "10px";
        cell13.appendChild(buttonEdit);
        cell13.appendChild(buttonDelete);

        const cell14 = document.createElement('td');
        cell14.textContent = data.timeSheetId;
        cell14.style.display = "none";

        // Thêm các ô vào hàng
        row.appendChild(cell0);
        row.appendChild(cell00);
        row.appendChild(cell14);
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
        if (positionAdd == "fisrt") {

            actionTableBody.insertBefore(row, actionTableBody.firstChild);
        } else {
            actionTableBody.appendChild(row);
        }

        buttonDelete.addEventListener('click', function () {
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

        buttonEdit.addEventListener('click', function () {
            const row = this.closest('tr');

            const cells = row.querySelectorAll('td');
            console.log(cells);
            uidEdit = cells[0].textContent;
            categoryTimeSheet.value = cells[2].textContent;
            lessonTopic.value = cells[9].textContent;
            studentErrorCount.value = cells[10].textContent;
            detailErrorStudent.value = cells[11].textContent;
            generalComment.value = cells[12].textContent;
            lessonEvaluation.value = cells[13].textContent;
            cells[14].textContent === "Đã dạy" ? (isTrain.checked = true) : (isTrain.checked = false);

            const dataadd = {
                thu: cells[3].textContent,
                categoryPeriodName: cells[4].textContent,
                categoryClassName: cells[5].textContent,
                categoryRoomName: cells[6].textContent,
                categorySubjectName: cells[7].textContent,
                userFullName: cells[8].textContent
            }
            addRowToTableView(dataadd)
        });
    }


    submitDataButton.addEventListener("click", function () {

        dataInput.value = JSON.stringify(listSoDauBai); // Gắn JSON vào input hidden
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
