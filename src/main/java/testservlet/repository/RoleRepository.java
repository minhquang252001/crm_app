  package testservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import testservlet.config.Mysqlconfig;
import testservlet.entity.RoleEntity;

public class RoleRepository {
	public List<RoleEntity> findByOne(int id){
		List<RoleEntity> listRoleEntities = new ArrayList<RoleEntity>();
		String query= "SELECT r.id,r.name,r.description FROM roles r WHERE r.id = ?";
		try {
			Connection connection = Mysqlconfig.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDesc(resultSet.getString("description"));
				roleEntity.setId(resultSet.getInt("id"));
				listRoleEntities.add(roleEntity);
			}
		}catch (Exception e) {
			System.out.println("Lỗi findOne " +e.getLocalizedMessage());
		}
		
		return listRoleEntities;
	}

	public int updateById(String roleName,String roleDesc,int id) {
		int count = 0;
		String query="UPDATE roles SET name =?, description =? WHERE id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, roleName);
			preparedStatement.setString(2, roleDesc);
			preparedStatement.setInt(3, id);
			count = preparedStatement.executeUpdate();
		}catch (Exception e) {
			System.out.println("Lỗi Role_updateById" + e.getLocalizedMessage());
		}
		return count;
	}
	
	public int insert(String name, String desc) {
		int count = 0;
		try {
			String query ="INSERT INTO roles (name,description) VALUES(?,?)";
			Connection connection = Mysqlconfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, desc);
			
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("lỗi Insert Role " + e.getLocalizedMessage());
		}
		return count;
		
	}
	
	public int deleteById(int id) {
		int count = 0;
		String query="Delete from roles u where u.id = ? ";
		try{
			Connection connection = Mysqlconfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			count = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi deleteById " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public List<RoleEntity> findAll() {
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles r ";
		try {
			Connection connection = Mysqlconfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt("id"));
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDesc(resultSet.getString("description"));
				listRole .add(roleEntity);
			}
			
						
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi findAll" + e.getLocalizedMessage());
		}
		return listRole;
		
	}
	
	
	
}
