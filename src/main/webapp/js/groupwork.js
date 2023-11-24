$(document).ready(function (){
	$("#btn-add").click(function (){
	var name = $("#job-name").val();
    var startdate = $("#start").val();
    var enddate = $("#end").val();
    
    //So sánh ngày bắt đầu phải trước ngày kết thúc.
	var startDate = new Date(document.getElementById("start").value);
	var endDate = new Date(document.getElementById("end").value);
	
	if (name.trim() == "") {
      alert("Vui lòng nhập tên dự án");
      return;
    }
	if (name == "" || startdate == "" || enddate == "") {
      alert("Vui lòng nhập đầy đủ thông tin");
      return;
    } 
	if (endDate < startDate) {
	  alert("Ngày kết thúc phải sau ngày bắt đầu dự án");
	  return;
	}
    
	
    $.ajax({
      method: "post",
      url: "http://localhost:8080/testservlet//api/groupwork",
      data: { name: name, startdate: startdate, enddate: enddate }
    }).done(function (result) {
      if (result.data == true) {
        alert("Inser Successful ");
      } else {
        alert("Inser Failer");
      }
    });
    });
})