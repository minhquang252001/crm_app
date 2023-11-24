$(document).ready(function () {
	$(".btn-delete").click(function () {
		var id = $(this).attr("id-task")
		var This= $(this)
		if(confirm("Bạn có chắc chắn muốn xóa hay không")){
		$.ajax({
			method:"DELETE",
			url:"http://localhost:8080/testservlet/api/task-delete?id=" + id
		
		}).done(function (result){
			if(result.data == true){
				This.closest("tr").remove()
				alert("Xóa thành công")
			}else{
				alert("Xóa thất bại")
			}
		
		})
		}
		else{
		alert("Hủy xóa")
		}
	})
})