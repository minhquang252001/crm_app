$(document).ready(function(){
	//logic Code
	
	$(".btn-xoa").click(function(){
	var id = $(this).attr("id-role")
	var This = $(this)
	//Gọi đường dẫn và lấy dữ liệu từ đường dẫn đó.
	if(confirm("Bạn có chắc chắn muốn xóa không?")){
	$.ajax({
		  method: "DELETE",
		  url: "http://localhost:8080/testservlet/api/role?id=" + id,
		})
		  .done(function( result ) {
		    if(result.data == true){
		    	This.closest("tr").remove()
		    	alert("Xóa thành công")
		    }else{
		    	alert("Xóa thất bại");
		    }
		    console.log(result);   
		  });
		  }
		else{
		alert("Hủy xóa");
		}
	
	})
})