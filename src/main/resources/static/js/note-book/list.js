document.addEventListener("DOMContentLoaded", function () {


    const btn_check = document.getElementById("btn-check-teach");

    const table = document.getElementById("table-action-note-book");
    const inputUpdateStatus = document.getElementById("data-update-status");
    const checkboxAll= document.getElementById("check-box-all");
    const rowTable = table.querySelectorAll("tbody tr");
    const formAction = document.getElementById("form-action");

    checkboxAll.addEventListener("change", function(){
        if(checkboxAll.checked == true){
            rowTable.forEach(row => {
                const checkbox = row.querySelector('input.form-check-input'); 
                checkbox.checked = true;
            });
        }else{
            rowTable.forEach(row => {
                const checkbox = row.querySelector('input.form-check-input'); 
                const cells = row.children;
                if(cells[1].textContent == "false"){
                    checkbox.checked = false;
                }                
            });
        }
    })


    btn_check.addEventListener("click", function () {       
        var listData =[];
        rowTable.forEach(row => {
            const cells = row.children;
            console.log(cells[1]);
            const checkbox = row.querySelector('input.form-check-input'); 
            
            const data = {
                "isTrain" :checkbox.checked,
                "id": cells[2].textContent
            }
            listData.push(data);
        });
        inputUpdateStatus.value = JSON.stringify(listData);
         formAction.submit();
    });

    // submitDataButton.addEventListener("click", function () {

    //     dataInput.value = JSON.stringify(listSoDauBai); // Gắn JSON vào input hidden
    //     selectedDataForm.submit(); // Gửi form

    // });


    // Notification.requestPermission().then(permission => {
    //     if (permission === "granted") {
    //         alert("Thông báo đã được kích hoạt!");
    //     } else {
    //         alert("Bạn đã từ chối thông báo.");
    //     }
    // });

});

function Delete(id){
    Swal.fire({
        title: "Bạn có chắc chắn muốn xóa dữ liệu?",
        text: "Sau khi xóa, bạn sẽ không thể phục hồi dữ liệu này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Có, xóa nó!',
        cancelButtonText: 'Hủy',
        customClass: {
          title: 'swal-title',  // Áp dụng lớp tùy chỉnh cho tiêu đề
          popup: 'swal-popup',  // Áp dụng lớp tùy chỉnh cho toàn bộ popup
          confirmButton: 'swal-confirm-btn' // Nút xác nhận tùy chỉnh
        }
      }).then((result) => {
        if (result.isConfirmed) { 
          window.location.href = "/delete/" + id;
        }
      });
      
}