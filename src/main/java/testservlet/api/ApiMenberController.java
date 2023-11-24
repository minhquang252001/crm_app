package testservlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testservlet.Service.MenberService;
import testservlet.payload.BasicResponse;

@WebServlet (name = "ApiMenber",urlPatterns = {"/api/user-add","/api/user-delete","/api/user-update"})
public class ApiMenberController extends HttpServlet{
	

	private MenberService menberService = new MenberService();
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/api/user-add")) {
			doPostInser(req, resp);
		}else if(path.equals("/api/user-update")) {
			doPutUpdate(req, resp);
		}
	}
	
	private void doPutUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String username = req.getParameter("username");
		int idRole = Integer.parseInt(req.getParameter("idRole"));
		boolean isSucces = menberService.getUpdateMenber(id, firstname, lastname, username, idRole);
		System.out.println("Kiểm tra doPut " + isSucces);
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSucces);
		String dataJsonPut = gson.toJson(basicResponse);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
        out.print(dataJsonPut);
        out.flush();
	}
	
	
	private void doPostInser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String username = req.getParameter("username");
		int idRole = Integer.parseInt(req.getParameter("idRole"));
		boolean isSuccess = menberService.inserMenber(firstname, lastname, username, idRole);
		System.out.println("Kiểm Tra doPost " + isSuccess);
		
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		String dataJson = gson.toJson(basicResponse);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
        out.print(dataJson);
        out.flush();
      
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = menberService.deleteMenber(id);
		System.out.println("Kiểm tra doDelete " + id );
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		
		String dataJon1 = gson.toJson(basicResponse);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		out.print(dataJon1);
		out.flush();
		
	}
}
