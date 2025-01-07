document.addEventListener("DOMContentLoaded", function () {
        const calendarBody = document.getElementById("calendar-body");
        const monthYear = document.getElementById("month-year");
        const prevMonth = document.getElementById("prev-month");
        const nextMonth = document.getElementById("next-month");
        const categorySelect = document.getElementById("categorySelect");
        const isActive = document.getElementById("isActive");
        const caPhan =  document.getElementById("ca");

        const actionTableBody = document.getElementById("action-table-body");
        const submitDataButton = document.getElementById("submit-data");
        const selectedDataForm = document.getElementById("selectedDataForm");
        const dataInput = document.getElementById("dataInput");
        const timeLine = document.getElementById("timeLine");
        
         // Lấy ngày hiện tại
    const now = new Date();
    const year = now.getFullYear();
    const month = (now.getMonth() + 1).toString().padStart(2, '0'); // Thêm số 0 nếu tháng < 10

    // Đặt giá trị mặc định cho input
    const inputMonth = document.getElementById('thangPhanCong');
      inputMonth.value = `${year}-${month}`;

        let labelGiaoVien = "";
        let labelCaPhan ="";

        let currentDate = new Date();
        const markedDates = [];
        const selectedData = []; // Dữ liệu sẽ chứa danh sách ngày và loại đã chọn

        renderCalendar(currentDate);

        prevMonth.addEventListener("click", () => {
            currentDate.setMonth(currentDate.getMonth() - 1);
            renderCalendar(currentDate);
        });

        nextMonth.addEventListener("click", () => {
            currentDate.setMonth(currentDate.getMonth() + 1);
            renderCalendar(currentDate);
        });

        categorySelect.addEventListener("change", function () {
            labelGiaoVien = categorySelect.options[categorySelect.selectedIndex].text;
        });

        caPhan.addEventListener("change", function () {
                    labelCaPhan = caPhan.options[caPhan.selectedIndex].text;
                });

        function renderCalendar(date) {
            const month = date.getMonth();
            const year = date.getFullYear();

            monthYear.textContent = `Tháng ${["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"][month]} ${year}`;
            calendarBody.innerHTML = "";

            const firstDay = new Date(year, month, 1).getDay();
            const daysInMonth = new Date(year, month + 1, 0).getDate();

            let row = document.createElement("tr");

            for (let i = 0; i < firstDay; i++) {
                row.appendChild(document.createElement("td"));
            }

            for (let day = 1; day <= daysInMonth; day++) {
                const cell = document.createElement("td");
                cell.textContent = day;

                const formattedDate = `${year}-${String(month + 1).padStart(2, "0")}-${String(day).padStart(2, "0")}`;

                if (markedDates.includes(formattedDate)) {
                    cell.classList.add("marked");
                }


                cell.addEventListener("click", function () {
                    const selectedCategory = categorySelect.value;
                    const ca = caPhan.value;
                     labelGiaoVien =categorySelect.options[categorySelect.selectedIndex].text;
                     labelCaPhan = caPhan.options[caPhan.selectedIndex].text
                    
                    if (selectedData.some(item => item.date === formattedDate)) {
                        alert("Ngày này đã được chọn!");
                        return;
                    }

                    markedDates.push(formattedDate);
                    selectedData.push({ id: null, date: formattedDate, idUser: selectedCategory, labelGv: labelGiaoVien, codeCa: ca, nameCa: labelCaPhan  });
                    addRowToTable(formattedDate, selectedCategory,labelGiaoVien, ca, labelCaPhan);
                     cell.classList.add("marked");
                });

                row.appendChild(cell);
                if ((firstDay + day) % 7 === 0 || day === daysInMonth) {
                    calendarBody.appendChild(row);
                    row = document.createElement("tr");
                }
            }
        }

        function addRowToTable(date, idUser,category, ca, labelCaPhan) {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${date}</td>
                <td>${category}</td>
                <td>${labelCaPhan}</td>
                <td><button class="remove-btn">Xóa</button></td>
            `;
            actionTableBody.appendChild(row);

            row.querySelector(".remove-btn").addEventListener("click", function () {
                const index = selectedData.findIndex(item => item.date === date && item.idUser === idUser && item.codeCar ===ca) ;

                if (index !== -1) {
                    selectedData.splice(index, 1);
                    row.remove();
                    markedDates.splice(index,1);
                     // Tìm ô lịch (calendar cell) tương ứng và xóa lớp `marked`
                     const cells = calendarBody.querySelectorAll("td");
                      cells.forEach(cell => {
                        if (cell.textContent === String(new Date(date).getDate())) {
                            cell.classList.remove("marked");
                      }
                      });
                }


            });
        }

        submitDataButton.addEventListener("click", function () {
            if (selectedData.length > 0) {
                dataInput.value = JSON.stringify(selectedData); // Gắn JSON vào input hidden
                const dataTimeLine = {isActive: isActive.value, month: inputMonth.value }
                timeLine.value = JSON.stringify(dataTimeLine);
                selectedDataForm.submit(); // Gửi form
            } else {
                alert("Bạn chưa chọn ngày nào!");
            }
        });
    });