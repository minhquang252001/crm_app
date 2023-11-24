package testservlet.Service;

import java.util.List;

import testservlet.entity.JobsEntity;
import testservlet.repository.JobsRepository;

public class JobsService {
	private JobsRepository jobsRepository = new JobsRepository();
	
	public List<JobsEntity> getAll() {
		return jobsRepository.findAll();
		
	}
	public boolean getInset(String name, String startdate,String enddate) {
		return jobsRepository.insert(name, startdate, enddate) >0;
	}
	
	public boolean getDelete(int id) {
		return jobsRepository.deleteJobs(id) > 0;
	}
	
	public List<JobsEntity> getfinByOne(int id){
		return jobsRepository.findByOne(id);
	}
	
	public boolean getUpdateJobs(int id,String name, String startdate,String enddate) {
		return jobsRepository.updateJobs(id, name, startdate, enddate) > 0;
	}
}
