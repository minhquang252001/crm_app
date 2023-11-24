$(document).ready(function (){
	$("#btn-update").click(function (){
	var id= $("#id").val()
	var name = $("#job-name").val()
	var startDate = $("#start").val()
	var endDate = $("#end").val()
	var start = new Date(document.getElementById("start").value); 
	var end = new Date(document.getElementById("end").value)
	if(name.trim() ==""){
		alert("Vui lòng nhập tên dự án")
		return;
	}
	if(start > end){
		alert("Ngày kết thúc dự án phải sau ngày bắt đầu dự án")
		return;
	}
	if(name == "" || startDate =="" || endDate == ""){
	alert("Vui lòng nhập đầy đủ thông tin")
	return;
	}
		$.ajax({
		method:"PUT",
		url: "http://localhost:8080/testservlet//api/groupwork-update?id=" + id + "&name=" + name + "&startDate=" + startDate + "&endDate=" + endDate,
		})
		.done(function (result) {
		if(result.data == true){
		alert("Update Successful")
		}else {
		alert("Update Failed")
		}
		})
	})
})