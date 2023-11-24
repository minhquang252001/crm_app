package testservlet.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testservlet.config.Mysqlconfig;
import testservlet.entity.UserEntity;


@WebFilter (filterName = "aothenFilter",urlPatterns = {"/role-add"})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		System.out.println("đã kích hoạt filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		
		String email ="";
		String password="";
		String contextPath = req.getContextPath();
		// Lấy danh sách cookie người dùng gửi lên thông qua 
		try {
			Cookie[] cookie = req.getCookies();
			for (Cookie cookie2 : cookie) {
				if(cookie2.getName().equals("email")) {
					email = cookie2.getValue();
				}
				if(cookie2.getName().equals("password")) {
					password = cookie2.getValue();
				}
			}
			if(email.trim().length()> 0 && password.trim().length()>0){
				//đã đăng nhập rổi
				//B1 mở truy vấn CSDL
				String query="SELECT * \r\n"
						+ "FROM users u\r\n"
						+ "WHERE u.email  = ? AND u.password = ?";
				//bước 3: mở kết nối csdl
				Connection connection = Mysqlconfig.getConnection();
				
				//Bước 4:Truyền câu Query vào csdl vừa mở kết nối thông qua PrepareStatement
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					// Gắn giá trị tham số dấu chấm ? bên trong  câu query
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					
					//Bước 5: Thông Báo cho CSDL biết và thực thi câu 
					//
					ResultSet resultSet = preparedStatement.executeQuery();
					//
					List<UserEntity> listUser = new ArrayList<UserEntity>();
					// Bước 6: Duyệt từng dòng dữ liệu query được gắn
					while (resultSet.next()) {
						UserEntity entity = new UserEntity();
						entity.setId(resultSet.getInt("id"));
						entity.setFullname(resultSet.getString("fullname"));
						listUser.add(entity);
					}
					// kiểm tra đăng nhập bằng cách kiểm tra xem listUser có giá trị hay không
					if (listUser.size() > 0) {
						System.out.println("Đăng nhập thành công");
						chain.doFilter(req, resp);
					}else {
						System.out.println("Đăng nhập thất bại");
						resp.sendRedirect(contextPath+ "/login");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.sendRedirect(contextPath+ "/login");
				}
				
				// cho phép đi vào link mà người dùng đang gọi mà kích hoạt filter
				
			}else {
				resp.sendRedirect(contextPath+ "/login");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi "+ e.getMessage());
		}

		
		
	}
		
}
