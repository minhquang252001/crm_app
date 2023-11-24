<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>




<div class="container">
  <div class="row mt-5">
    <div class="col-md-5 m-auto mt-5">
      <h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
      <div class="p-4 border mt-4">
        <form action="/testservlet/login" method="post">
            <div class="form-group">
              <label>Email</label>
              <input type="email" class="form-control" name="email" id="email">
            </div>      
            <div class="form-group">
              <label>Mật khẩu</label>
              <input type="password" class="form-control" name="password" id="password">
            </div>
             <div class="form-group">
             <label>Nhập lại mật khẩu</label>
              <input type="password" class="form-control" name="password1" id="password1">
            </div>
            <div class="form-group">
             <label>Họ và tên</label>
              <input type="text" class="form-control" name="name" id="name">
            </div>
           	<a href="#" type="submit" id="login-add" class="btn btn-primary">Đăng Ký ngay</a>
          </form>
      </div>
      </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/login-add.js"></script>
</body>
</html>
    