$(document).ready(function () {
    $("#btn-add").click(function () {
        var firstname = $("#firstname").val()
        var lastname = $("#lastname").val()
        var username = $("#username").val()
        var roleId = $("#roleuser option:selected").val()

        if(firstname == "" || lastname == "" || username == "") {
            alert("Vui lòng nhập đầy đủ thông tin")
            return;
            }

        $.ajax({
            method: "post",
            url: "http://localhost:8080/testservlet/api/user-add",
            data: { firstname: firstname, lastname: lastname, username: username, idRole: roleId }
            
        }).done(function( result ) {
            if(result.data == true){
            	alert("Thêm thành viên thành công")
            }else{
            	alert("Thêm thành viên thất bại")
            }
        });
    })
})
