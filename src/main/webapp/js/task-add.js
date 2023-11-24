$(document).ready(function () {
	$("#btn-add").click(function (){
	var name = $("#name").val()
	var jobId = $("#jobId option:selected").val()
	var menberId = $("#menberId option:selected").val()
	var start = $("#startDate").val()
	var end = $("#endDate").val()
	var status = $("#statusId option:selected").val()
	//Ngày kết thúc phải sau ngày bắt đầu
	var start1 = new Date(document.getElementById("startDate").value); 
	var end1 = new Date(document.getElementById("endDate").value)
	if(name == ""){
	alert('Vui lòng nhập Tên công việc')
		return;
	}else if(start1 > end1){
		alert("Ngày kết thúc dự án phải sau ngày bắt đầu dự án")
		return;
	}
		$.ajax({
		method:"post",
		url:"http://localhost:8080/testservlet/api/task-add",
		data:{name:name, jobId:jobId, menberId:menberId, start:start, end:end, status:status}
		}).done(function ( result ){
			if(result.data == true){
            	alert("Thêm thành viên thành công")
            }else{
            	alert("Thêm thành viên thất bại")
            }
		})
	})
})