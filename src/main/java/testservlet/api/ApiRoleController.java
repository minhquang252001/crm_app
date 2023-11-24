package testservlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testservlet.Service.RoleService;
import testservlet.payload.BasicResponse;

@WebServlet(name = "apiRoleController",urlPatterns = {"/api/role","/api/roleUpdate","/api/roleInser"})
public class ApiRoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	
	private Gson gson = new Gson();
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = roleService.deleteRole(id);
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
		
		System.out.println("Kiểm tra delete " + id);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("roleName");
		String desc = req.getParameter("roleDesc");
		boolean isSuccess = roleService.updateRole(name, desc, id);
		System.out.println("Kiểm tra doPut " + isSuccess);
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		
		String dataJson1 = gson.toJson(basicResponse);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
        
        out.print(dataJson1);
        out.flush();  
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("rname");
		String desc = req.getParameter("rdesc");
		boolean isSuccess = roleService.insert(name, desc);

		System.out.println("Kiểm tra doPost " + isSuccess);
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setStatusCode(200);
		basicResponse.setMessage("");
		basicResponse.setData(isSuccess);
		
		String dataJon = gson.toJson(basicResponse);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		out.print(dataJon);
		out.flush();
	}
	
	
}
