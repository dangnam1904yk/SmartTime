document.addEventListener("DOMContentLoaded", function () {
    const submitDataButton = document.getElementById('submit-data');
    const dataInput = document.getElementById('data');
    const table = document.getElementById('table-check-noon');
    const selectedDataForm=  document.getElementById('selectedDataForm');
    const rows = table.querySelectorAll('tbody tr');

    submitDataButton.addEventListener("click", function () {
        const dataSend = [];
        rows.forEach(row => {
            const checkbox = row.querySelector('input.form-check-input'); // Lấy checkbox trong hàng

            let id = row.cells[1].textContent;
            let isCheck = false
            if (checkbox.checked) {
                isCheck = true;
            }
            dataSend.push({ id: id, isCheck: isCheck })
        });
        dataInput.value = JSON.stringify(dataSend); // Gắn JSON vào input hidden

        selectedDataForm.submit(); // Gửi form

    });
});