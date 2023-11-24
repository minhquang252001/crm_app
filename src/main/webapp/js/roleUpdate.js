$(document).ready(function (){

	$("#btn-update").click(function (){
		var id = $("#roleId").val()
		var name = $("#roleName").val()
		var desc = $("#roleDesc").val()
		if (name == "" || desc ==""){
		alert("Vui lòng  nhập đầy đủ không bỏ trống")
		return;
		}
			$.ajax({
			  method: "PUT",
			  url: "http://localhost:8080/testservlet/api/roleUpdate?id=" + id + "&roleName=" + name + "&roleDesc=" + desc
			})
			  .done(function( result ) {
			   if(result.data == true){
			   		alert("Update successful");
			   } else {
			   		alert("Update failed");
			   }			   
			  });
		
	});
});