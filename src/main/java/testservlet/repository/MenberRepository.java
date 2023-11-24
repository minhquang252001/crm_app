package testservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import testservlet.config.Mysqlconfig;
import testservlet.entity.MenberEntity;
import testservlet.entity.RoleEntity;

public class MenberRepository {
		
	public List<MenberEntity>findAllMenber(){
		List<MenberEntity> listMenber = new ArrayList<MenberEntity>();
		String query ="SELECT m.id,m.firstname ,m.lastname ,m.username ,r.name FROM members m\r\n"
				+ "join roles r \r\n"
				+ "where m.role_id  = r.id;";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MenberEntity entity = new MenberEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setFirstname(resultSet.getString("firstname"));
				entity.setLastname(resultSet.getString("lastname"));
				entity.setUsername(resultSet.getString("username"));
				entity.setRole_name(resultSet.getString("name"));
				listMenber.add(entity);
			}
			
		} catch (SQLException e) {
			System.out.println("Lỗi findAllMenber " + e.getLocalizedMessage());
		}
		return listMenber;
	}
	
	public List<RoleEntity>findNameRole(){
		List<RoleEntity> listRole1 = new ArrayList<RoleEntity>();
		String query =" SELECT r.id , r.name\r\n"
				+ "FROM roles r";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				RoleEntity entity = new RoleEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				listRole1.add(entity);
			}
		}catch (Exception e) {
			System.out.println("lỗi findNameRole " + e.getLocalizedMessage());
		}
		return listRole1;
	}
	
	public int insertMenber(String firstname,String lastname,String username , int roleuser) {
		int count = 0;
		String query= "INSERT INTO members(firstname,lastname,username,role_id) VALUES(?,?,?,?)";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, username);
			preparedStatement.setInt(4, roleuser);
			count = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("Lỗi insert-Menber" + e.getLocalizedMessage());
		}
		return count;
	}
	public int deleteMenber(int id) {
		int count = 0;
		String query ="DELETE FROM members WHERE id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		count = preparedStatement.executeUpdate();
		}catch (Exception e) {
			System.out.println("Lỗi deleteMenber " + e.getLocalizedMessage());
		}
		return count;
	}
	public List<MenberEntity> findByMenBer (int id) {
		List<MenberEntity> list = new ArrayList<MenberEntity>();		
		String query ="SELECT m.id ,m.firstname ,m.lastname ,m.username\r\n"
				+ "FROM members m \r\n"
				+ "WHERE m.id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MenberEntity menberEntity = new MenberEntity();
				menberEntity.setId(resultSet.getInt("id"));
				menberEntity.setFirstname(resultSet.getString("firstname"));
				menberEntity.setLastname(resultSet.getString("lastname"));
				menberEntity.setUsername(resultSet.getString("username"));
				list.add(menberEntity);
			}
		}catch (Exception e) {
			System.out.println("Lỗi updateByMenBer " + e.getLocalizedMessage());
		}		
		return list;
	}
	public int updateMenBer(int id, String firstname,String lastname,String username,int roleUser) {
		int count = 0;
		String query="Update members SET firstname = ? ,lastname = ? ,username= ? ,role_id = ?  WHERE id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, username);
			preparedStatement.setInt(4, roleUser);
			preparedStatement.setInt(5, id);
			count = preparedStatement.executeUpdate();
		}catch (Exception e) {
			System.out.println("Lỗi updateMenBer " + e.getLocalizedMessage());
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
