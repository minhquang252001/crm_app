package testservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.EndDocument;

import testservlet.config.Mysqlconfig;
import testservlet.entity.JobsEntity;
import testservlet.entity.MenberEntity;
import testservlet.entity.TaskEntity;

public class TaskRepository {

	public List<TaskEntity> finAll(){
		List<TaskEntity> list = new ArrayList<TaskEntity>();
		String query="SELECT t.id ,t.name,j.name,m.lastname, DATE_FORMAT(t.start_date, '%d-%m-%Y') AS start_date ,DATE_FORMAT(t.end_date, '%d-%m-%Y') AS end_date,s.name\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN jobs j ON t.job_id = j.id \r\n"
				+ "JOIN members m ON t.menber_id = m.id \r\n"
				+ "JOIN status s ON t.status_id =s.id";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(resultSet.getInt("t.id"));
				entity.setName(resultSet.getString("t.name"));
				entity.setStart_date(resultSet.getString("start_date"));
				entity.setEnd_date(resultSet.getString("end_date"));
				entity.setJob_id(resultSet.getString("j.name"));
				entity.setMenber_id(resultSet.getString("m.lastname"));
				entity.setStatus_id(resultSet.getString("s.name"));
				list.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Lỗi findAll Task " + e.getLocalizedMessage());
		}
		return list;
	}
	public List<JobsEntity> findJobs(){
		List<JobsEntity> list = new ArrayList<JobsEntity>();
		String query="SELECT id ,name \r\n"
				+ "FROM jobs";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				JobsEntity entity = new JobsEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				list.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Lỗi findJobs Task " + e.getLocalizedMessage());
		}
		return list;
	}
	public List<MenberEntity> findMenber(){
		List<MenberEntity> list = new ArrayList<MenberEntity>();
		String query="SELECT id ,lastname \r\n"
				+ "FROM members";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				MenberEntity entity = new MenberEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setLastname(resultSet.getString("lastname"));
				list.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Lỗi findMenber Task " + e.getLocalizedMessage());
		}
		return list;
	}
	public List<JobsEntity> findStatus(){
		List<JobsEntity> list = new ArrayList<JobsEntity>();
		String query="select id,name \r\n"
				+ "from status s";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				JobsEntity entity = new JobsEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				list.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Lỗi findStatus Task " + e.getLocalizedMessage());
		}
		return list;
	}
	
	public int insetTask(String name,String start , String end, int menberId, int jobId, int statusId) {
		int count = 0;
		String query="INSERT INTO tasks (name, start_date, end_date, menber_id, job_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, start);
			preparedStatement.setString(3, end);
			preparedStatement.setInt(4, menberId);
			preparedStatement.setInt(5, jobId);
			preparedStatement.setInt(6, statusId);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi insert Task " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public int delete(int id) {
		int count = 0;
		String query="DELETE FROM tasks WHERE id= ?";
		Connection connection= Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi delete Task " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public int updateTask(int id,String name,String start,String end,int menber_id,int job_id,int status_id ){
		int count = 0;
		String query="Update tasks SET name = ? ,start_date = ?, end_date =?, menber_id = ?, job_id = ?,status_id =? WHERE id =?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, start);
			preparedStatement.setString(3, end);
			preparedStatement.setInt(4, menber_id);
			preparedStatement.setInt(5, job_id);
			preparedStatement.setInt(6, status_id);
			preparedStatement.setInt(7, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi Update Task " + e.getLocalizedMessage());
		}
		return count;
	}
	public List<TaskEntity> finByTask(int id) {
		List<TaskEntity> list = new ArrayList<TaskEntity>();
		String query="SELECT t.id ,t.name ,t.start_date ,t.end_date \r\n"
				+ "from tasks t\r\n"
				+ "where id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStart_date(resultSet.getString("start_date"));
				entity.setEnd_date(resultSet.getString("end_date"));
				list.add(entity);
			}

		} catch (Exception e) {
			System.out.println("Lỗi Update TaskId");
		}
		return list;
		
	}
	

}
