package testservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testservlet.Service.TaskService;
import testservlet.entity.JobsEntity;
import testservlet.entity.MenberEntity;
import testservlet.entity.TaskEntity;


@WebServlet(name = "task", urlPatterns = {"/task-table","/task-add","/task-update"})  
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/task-table")) {
			doGetTable(req, resp);
		}else if(path.equals("/task-add")) {
			doGetAdd(req, resp);
		}else if(path.equals("/task-update")) {
			doGetUpdate(req, resp);
		}
	}
	private void doGetTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TaskEntity> list = taskService.getAll();
		req.setAttribute("listTable", list);
		
		req.getRequestDispatcher("task.jsp").forward(req, resp);
	}
	private void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobsEntity> list = taskService.getOneJobs();
		req.setAttribute("listJobs", list);
		
		List<MenberEntity> list2 = taskService.getOneMember();
		req.setAttribute("listMenber", list2);
		
		List<JobsEntity> list3 = taskService.getOneStatus();
		req.setAttribute("listStatus", list3);
		
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}
	private void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobsEntity> list = taskService.getOneJobs();
		req.setAttribute("listJobs", list);
		
		List<MenberEntity> list2 = taskService.getOneMember();
		req.setAttribute("listMenber", list2);
		
		List<JobsEntity> list3 = taskService.getOneStatus();
		req.setAttribute("listStatus", list3);
		int id = Integer.parseInt(req.getParameter("id"));
		List<TaskEntity> list4 = taskService.getfinByTask(id);
		req.setAttribute("listTask", list4);
		
		req.getRequestDispatcher("task-update.jsp").forward(req, resp);
	}
	
	
}
