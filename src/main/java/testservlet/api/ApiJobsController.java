package testservlet.api;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testservlet.Service.JobsService;
import testservlet.payload.BasicResponse;

@WebServlet(name="ApiJobs",urlPatterns = {"/api/groupwork","/api/groupwork-delete","/api/groupwork-update"})
public class ApiJobsController extends HttpServlet{
	private JobsService jobsService = new JobsService();
	private Gson gson = new Gson();
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String name = req.getParameter("name");
			String startdate = req.getParameter("startdate");
			
			String enddate = req.getParameter("enddate");
			System.out.println("Kiểm tra name " + name);
			System.out.println("Kiểm tra startdate " + startdate);
			System.out.println("Kiểm tra enddate " + enddate);
			boolean isSuccess = jobsService.getInset(name, startdate, enddate);
			
			BasicResponse basicResponse = new BasicResponse();
			basicResponse.setStatusCode(200);
			basicResponse.setMessage("");
			basicResponse.setData(isSuccess);
			
			String dataJonPost = gson.toJson(basicResponse);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
	        out.print(dataJonPost);
	        out.flush();	
		}
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			int id = Integer.parseInt(req.getParameter("id"));
			
			boolean isSuccess =jobsService.getDelete(id);
			
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
			int id= Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String start = req.getParameter("startDate");
			String end= req.getParameter("endDate");
			
			boolean isSuccess = jobsService.getUpdateJobs(id, name, start, end);
			
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
