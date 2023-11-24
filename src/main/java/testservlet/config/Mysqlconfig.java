package testservlet.config;
/** 
 * 	Class dùng để  khai báo thông tin cấu hình tạo kết nối tới CSDL
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysqlconfig {
	
	public static Connection getConnection() {
		
		try {
		// Khai báo Driver sử dụng cho JDBC (từ khóa tên driver class.fornname)
			Class.forName("com.mysql.cj.jdbc.Driver");
		// Khai báo thông tin csdl mà JDBS sẽ kết nối tới
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "admin123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("lỗi kết nối CSDL "+ e.getLocalizedMessage());
		}
		return null;
	}
}
