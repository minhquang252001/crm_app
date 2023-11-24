package testservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testservlet.Service.MenberService;
import testservlet.entity.MenberEntity;
import testservlet.entity.RoleEntity;


@WebServlet (name="user",urlPatterns = {"/user-table","/user-add","/user-update"})
public class MenberController extends HttpServlet{

	private MenberService menberService = new MenberService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/user-table")) {
			doGetUserTable(req, resp);
		}else if(path.equals("/user-add")) {
			doGetUserRoleAdd(req, resp);
		}else if(path.equals("/user-update")) {
			doGetUserRoleUpdate(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doPostMenberRoleAdd(req, resp);
		
	}
	private void doGetUserRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		int id = Integer.parseInt(req.getParameter("id"));
		List<MenberEntity> list = menberService.getFindByUpdate(id);
		req.setAttribute("user", list.get(0));
		
		List<RoleEntity> list2 = menberService.getOneNameRole();
		req.setAttribute("listRole1", list2);
		System.out.println("Kiểm tra doGetUserRoleUpdate " + id);
		req.getRequestDispatcher("user-update.jsp").forward(req, resp);
	}
	
	private void doGetUserRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		List<RoleEntity> list2 = menberService.getOneNameRole();
		req.setAttribute("listRole1", list2);
		
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	private void doGetUserTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MenberEntity> list = menberService.getAllMenber();
		
		req.setAttribute("listMenber", list);

		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
}
