$(document).ready(function (){
	$(".btn-delete").click(function(){
	var id = $(this).attr("id-groupwork")
	var This = $(this)
	if(confirm("Bạn chắc chán muốn xóa hay không")){
		$.ajax({
		method: "DELETE",
		url:'http://localhost:8080/testservlet/api/groupwork-delete?id=' +id,
		}).done(function (result){
		if(result.data == true){
			This.closest("tr").remove()
			alert('Xóa thành công')
		}else {
			alert('Xóa thất bại')
		}
		})
		}else{
		alert('Hủy xóa')
		}
	})
})