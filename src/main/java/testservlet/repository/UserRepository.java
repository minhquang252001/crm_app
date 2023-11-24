package testservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import testservlet.config.Mysqlconfig;
import testservlet.entity.UserEntity;

/**
 * chứa tất cả câu query liên quan đến bảng user
 * select: đại diện cho chữ find
 * where: đại diện cho chữ by
 */
public class UserRepository {
	
		public	List<UserEntity> findByEmailAndPassword(String email, String password){
//		 Bước 2: Chuẩn bị câu query( Truy vấn)
		String query="SELECT * \r\n"
				+ "FROM users u\r\n"
				+ "WHERE u.email  = ? AND u.password = ?";
		//bước 3: mở kết nối csdl
		Connection connection = Mysqlconfig.getConnection();
		//Tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		//Bước 4:Truyền câu Query vào csdl vừa mở kết nối thông qua PrepareStatement
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// Gắn giá trị tham số dấu chấm ? bên trong  câu query
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			//Bước 5: Thông Báo cho CSDL biết và thực thi câu 
			//
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Bước 6: Duyệt từng dòng dữ liệu query được gắn
			while (resultSet.next()) {
				UserEntity entity = new UserEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setFullname(resultSet.getString("fullname"));
				listUser.add(entity);
			}
		
		
		
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lỗi findByEmailAndPassword "+ e.getLocalizedMessage());
			}
		return listUser;
	}
		public int inserUser(String email,String password,String name) {
			int count = 0;
			int roleId = 25;
			String query="INSERT INTO users (email,password,fullname,role_id) VALUES(?,?,?,?)";
			Connection connection = Mysqlconfig.getConnection();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setInt(4, roleId);
				count = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("Lỗi inser User" + e.getLocalizedMessage());
			}
			return count;
		}
		
		
}
