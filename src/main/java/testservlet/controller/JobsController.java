package testservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testservlet.Service.JobsService;
import testservlet.entity.JobsEntity;

@WebServlet (name = "job",urlPatterns = {"/groupwork","/groupwork-update"})
public class JobsController extends HttpServlet{
	private JobsService jobsService = new JobsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/groupwork")) {
			doGetTable(req, resp);
		}else if(path.equals("/groupwork-update")) {
			doGetUpdate(req, resp);
		}
		
	}
	
	private void doGetTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<JobsEntity> list = jobsService.getAll();
		
		req.setAttribute("jobs", list);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	}
	private void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<JobsEntity> list1 = jobsService.getfinByOne(id);
		req.setAttribute("groupwork", list1.get(0));
		
		req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
	}
	
}
