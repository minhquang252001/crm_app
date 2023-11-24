$(document).ready(function () {
	
	$("#btn-update").click(function (){
	var id = $("#id-user").val()
	var firstname = $("#first-name").val()
    var lastname = $("#last-name").val()
    var username = $("#user-name").val()
    var roleId = $("#role-user option:selected").val()
    	if(firstname == "" || lastname == "" || username == "") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
        }
    $.ajax({
            method: "post",
            url: "http://localhost:8080/testservlet/api/user-update",
            data: {id:id, firstname: firstname, lastname: lastname, username: username, idRole: roleId }
        }).done(function( result ) {
            if(result.data == true){
            	alert("Chỉnh sửa thành viên thành công")
            }else{
            	alert("Chỉnh sửa thành viên thất bại")
            }
        }); 
    })
})