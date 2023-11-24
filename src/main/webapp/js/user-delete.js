$(document).ready(function () {
	$(".btn-xoa").click(function(){
		var id = $(this).attr("idUser");
		var This = $(this);
		if(confirm("Bạn chắc chắn muốn xóa user này")){
			$.ajax({
				method: 'DELETE',
				url: 'http://localhost:8080/testservlet/api/user-delete?id=' + id,
			})
			.done(function( result ){
				if(result.data == true){
					This.closest("tr").remove();
					alert("Xóa Thành Công");
				}else{
					alert("Xóa Thất Bại");
				}
			});
		}else{
			alert("Hủy thành công");
		}
	});
});