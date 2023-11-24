$(document).ready(function () {
	$("#login-add").click(function (){
	var email = $("#email").val()
	var password = $("#password").val()
	var password1 = $("#password1").val()
	var name = $("#name").val()
	if(email == ""){
		alert("Vui lòng nhập Email")
		return;
	}else if(password == ""){
		alert("Vui lòng nhập Password")
		return;
	}else if(password1 == ""){
		alert("Vui lòng nhập lại Password")
		return;
	}else if(name == ""){
		alert("Vui lòng nhập Họ và tên")
		return;
	}else if (
      !email.includes("@gmail.com") &&
      !email.includes("@email.com") &&
      !email.includes("@hotmail.com") &&
      !email.includes("@yahoo.com")
    ) {
      alert("Vui lòng nhập đúng định dạng Email");
      return;
    }
	if(password == password1){
		$.ajax({
			method:"post",
			url:"http://localhost:8080/testservlet/login-add",
			data:{email: email, password: password, name:name}		
		}).done(function (result){
			if(result.data== true){
				alert("Đăng ký thành công")
			}else if(result.data==false){
				alert("Email đã tồn tại vui lòng nhập email khác")
			}
		
		})
		}else{
			alert("Mật khẩu nhập lại chưa đúng vui lòng nhập lại")
			return;
		}
		
	})
})