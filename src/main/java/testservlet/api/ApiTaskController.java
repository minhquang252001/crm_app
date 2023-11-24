package testservlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testservlet.Service.TaskService;
import testservlet.payload.BasicResponse;

@WebServlet (name="ApiTask",urlPatterns = {"/api/task-add","/api/task-delete","/api/task-update"})
public class ApiTaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int jobId = Integer.parseInt(req.getParameter("jobId"));
		int menberId = Integer.parseInt(req.getParameter("menberId"));
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		int status = Integer.parseInt(req.getParameter("status"));
		
		boolean isSuccess = taskService.getinsetTask(name, start, end, menberId, jobId, status);
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		
		String dataJsonPost = gson.toJson(basicResponse);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
        out.print(dataJsonPost);
        out.flush();
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = taskService.getDelete(id);
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		String dataJsonDelete = gson.toJson(basicResponse);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
        out.print(dataJsonDelete);
        out.flush();
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String start = req.getParameter("startDate");
		String end = req.getParameter("endDate");
		int menberId = Integer.parseInt(req.getParameter("menberId"));
		int jobId = Integer.parseInt(req.getParameter("jobId"));
		int status = Integer.parseInt(req.getParameter("statusId"));
		boolean isSuccess = taskService.getUpdate(id, name, start, end, menberId, jobId, status);
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		String dataJsonPut = gson.toJson(basicResponse);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
        out.print(dataJsonPut);
        out.flush();	
	}

	
}
