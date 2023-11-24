package testservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import testservlet.config.Mysqlconfig;
import testservlet.entity.JobsEntity;

public class JobsRepository {
	public List<JobsEntity> findAll(){
		List<JobsEntity> list = new ArrayList<JobsEntity>();
		String query="SELECT id,name, DATE_FORMAT(start_date, '%d-%m-%Y') AS start_date ,DATE_FORMAT(end_date, '%d-%m-%Y') AS end_date\r\n"
				+ "FROM jobs j";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				JobsEntity jobsEntity = new JobsEntity();
				jobsEntity.setId(resultSet.getInt("id"));
				jobsEntity.setName(resultSet.getString("name"));
				jobsEntity.setStart_date(resultSet.getString("start_date"));
				jobsEntity.setEnd_date(resultSet.getString("end_date"));
				list.add(jobsEntity);
			}
		} catch (Exception e) {
			System.out.println("Lỗi finAll Jobs " + e.getLocalizedMessage());
		}
		
		return list;
	}
	
	public int insert(String jobname,String startdate, String enddate) {
		int count = 0;
		String query ="INSERT INTO jobs (name,start_date,end_date) VALUES(?,?,?)";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, jobname);
			preparedStatement.setString(2, startdate);
			preparedStatement.setString(3, enddate);
			count = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Lỗi insert Jobs " + e.getLocalizedMessage());
		}
		
		return count;
	}
	
	public int deleteJobs(int id) {
		int count = 0;
		String query= "DELETE FROM jobs WHERE id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi deleteJobs " + e.getLocalizedMessage());
		}
		
		return count;
	}
	public List<JobsEntity> findByOne(int id){
		List<JobsEntity> list = new ArrayList<JobsEntity>();
		String query ="SELECT j.id ,j.name ,j.start_date ,j.end_date \r\n"
				+ "FROM jobs j \r\n"
				+ "WHERE j.id =?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				JobsEntity entity = new JobsEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStart_date(resultSet.getString("start_date"));
				entity.setEnd_date(resultSet.getString("end_date"));
				list.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Lỗi finByOne Jobs " + e.getLocalizedMessage());
		}
		return list;
	}
	
	public int updateJobs(int id,String name,String startDate,String endDate) {
		int count = 0;
		String query="UPDATE jobs SET name= ?, start_date =?,end_date =? WHERE id = ?";
		Connection connection = Mysqlconfig.getConnection();
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, startDate);
			preparedStatement.setString(3, endDate);
			preparedStatement.setInt(4, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi updateJobs "+e.getLocalizedMessage());
		}
		return count;
		
	}
}
