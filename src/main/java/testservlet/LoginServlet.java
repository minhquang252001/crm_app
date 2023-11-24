//package testservlet;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.mysql.cj.xdevapi.Result;
//import testservlet.config.Mysqlconfig;
//import testservlet.entity.UserEntity;
//
//@WebServlet(name ="loginServlet", urlPatterns = {"/login"})
///**
// * Controller: Chỉ dùng chứa các class khai báo đường dẫn và nhận tham số ( Lưu ý không xử lý logic code)
// * Service: chứa các class để xử lý logic code cho các controller tương ứng.
// * Repository: chứa các class trả ra dữ liệu các câu query liên quan đến các bảng trong database
// * Tức là chỉ thực thi câu query và trả ra kết quả câu query(lưu ý không xử lý logic code).
// */
//public class LoginServlet extends HttpServlet {
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		Cookie cookieuser = new Cookie("username", "nguyenvana");
////		cookieuser.setMaxAge(5*60*60);
////		resp.addCookie(cookieuser);
////		
////		Cookie cookiepassword = new Cookie("password", "123456");
////		resp.addCookie(cookieuser);
////		resp.addCookie(cookiepassword);
////		HttpSession session = req.getSession();
////		String cysoft = (String) session.getAttribute("cybersoft");
////		System.out.println("Session " + cysoft);
//		//Lấy danh sách cookie từ request của người dùng.
//		Cookie[] listCookie = req.getCookies();
//		String email = "";
//		String password ="";
//		// duyệt qua từng cookie bên trong list
//		for (Cookie cookie : listCookie) {
//			if (cookie.getName().equals("email")) {
//				email = cookie.getValue();
//				System.out.println("Giá Trị "+ cookie.getValue());
//			}
//			if (cookie.getName().equals("password")) {
//				password = cookie.getValue();
//				System.out.println("Giá Trị "+ cookie.getValue());
//			}
//		}
//		// trả giá trị(tham số) cho file jsp ở requeDispatcher
//		req.setAttribute("email", email);
//		req.setAttribute("password", password);
//		
//		
//		
//		
//		
//		// Hiển thị nội dung file html khi người dùng gọi link/login
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		// Bước 1: Nhận tham số  người dùng truyền lên
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");
//		String remember = req.getParameter("remember");
//		// Bước 2: Chuẩn bị câu query( Truy vấn)
//		String query="SELECT * \r\n"
//				+ "FROM users u\r\n"
//				+ "WHERE u.email  = ? AND u.password = ?";
//		//bước 3: mở kết nối csdl
//		Connection connection = Mysqlconfig.getConnection();
//		
//		//Bước 4:Truyền câu Query vào csdl vừa mở kết nối thông qua PrepareStatement
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			// Gắn giá trị tham số dấu chấm ? bên trong  câu query
//			preparedStatement.setString(1, email);
//			preparedStatement.setString(2, password);
//			
//			//Bước 5: Thông Báo cho CSDL biết và thực thi câu 
//			//
//			ResultSet resultSet = preparedStatement.executeQuery();
//			//
//			List<UserEntity> listUser = new ArrayList<UserEntity>();
//			// Bước 6: Duyệt từng dòng dữ liệu query được gắn
//			while (resultSet.next()) {
//				UserEntity entity = new UserEntity();
//				entity.setId(resultSet.getInt("id"));
//				entity.setFullname(resultSet.getString("fullname"));
//				listUser.add(entity);
//			}
//			// kiểm tra đăng nhập bằng cách kiểm tra xem listUser có giá trị hay không
//			if (listUser.size() > 0) {
//				System.out.println("Đăng nhập thành công");
//				if (remember !=null) {
//					Cookie cookie = new Cookie("email",email);
//					Cookie cookie2 = new Cookie("password",password);
//					cookie.setMaxAge(2*60*60);
//					cookie2.setMaxAge(2*60*60);
//					resp.addCookie(cookie);
//					resp.addCookie(cookie2);
//				}
//			}else {
//				System.out.println("Đăng nhập thất bại");
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("Kiểm Tra Post " + email + " - " + password);
//	
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
//
//	}
//}
