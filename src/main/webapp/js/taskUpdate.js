$(document).ready(function () {
	$("#btn-update").click(function (){
		var id = $("#id").val()
		var name = $("#name").val()
		var jobId = $("#jobId option:selected").val()
		var menberId = $("#menberId option:selected").val()
		var start = $("#startDate").val()
		var end = $("#endDate").val()
		var statusId = $("#statusId option:selected").val()
		// 
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
		method:"PUT",
		url:"http://localhost:8080/testservlet/api/task-update?id=" + id + "&name="+ name + 
		"&jobId=" +jobId + "&menberId=" + menberId + "&startDate=" + start + "&endDate=" + end + "&statusId=" + statusId,
		data:{id:id, name: name, jobId:jobId, menberId: menberId, start: start, end:end, statusId:statusId}
		}).done(function(result){
			if(result.data == true){
			alert("Update Thành Công")
			}else{
			alert("Update Thất Bại")
			}
		})
	})
})    