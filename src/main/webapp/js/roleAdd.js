$(document).ready(function () {
	$("#btnadd").click(function() {
	var rolename = $("#role-name").val()
	var roledesc = $("#role-desc").val()
	if(rolename == "" && roledesc == ""){
	alert("Vui lòng nhập đầy đủ thông tin")
	return;
	}
	if(rolename == ""){
	alert("Vui lòng nhập tên quyền")
	return;
	}else if(roledesc == ""){
	alert("Vui lòng nhập mổ tả")
	return;
	}
		$.ajax({
		method:"post",
		url:"http://localhost:8080/testservlet/api/roleInser",
		data:{ rname:rolename , rdesc:roledesc }
		}).done(function (result) {
		if(result.data == true){
			alert("Inser Successful")
		}else {
			alert("Inser Failer")
		}
		
		})
	})
})