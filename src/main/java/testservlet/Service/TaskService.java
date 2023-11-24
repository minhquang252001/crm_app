package testservlet.Service;

import java.util.List;

import org.apache.el.parser.BooleanNode;

import testservlet.entity.JobsEntity;
import testservlet.entity.MenberEntity;
import testservlet.entity.TaskEntity;
import testservlet.repository.TaskRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	
	public List<TaskEntity> getAll(){
		return taskRepository.finAll();
	}
	public List<JobsEntity> getOneJobs(){
		return taskRepository.findJobs();
	}
	public List<MenberEntity> getOneMember(){
		return taskRepository.findMenber();
	}
	public List<JobsEntity> getOneStatus(){
		return taskRepository.findStatus();
	}
	public boolean getinsetTask(String name,String start , String end, int menberId, int jobId, int statusId) {
		return taskRepository.insetTask(name, start, end, menberId, jobId, statusId) > 0;
	}
	public boolean getDelete(int id) {
		return taskRepository.delete(id) > 0;
	}
	public boolean getUpdate(int id,String name,String start,String end,int menber_id,int job_id,int status_id ) {
		return taskRepository.updateTask(id, name, start, end, menber_id, job_id, status_id) > 0;
	}
	public List<TaskEntity> getfinByTask(int id){
		return taskRepository.finByTask(id);
	}
}
