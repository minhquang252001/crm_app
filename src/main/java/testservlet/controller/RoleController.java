package testservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testservlet.Service.RoleService;
import testservlet.entity.RoleEntity;
import testservlet.repository.RoleRepository;
@WebServlet(name="roleServlet",urlPatterns = {"/role-add","/role-table","/role-update"} )
public class RoleController extends HttpServlet {
	
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Path = req.getServletPath();
		System.out.println("kiểm tra "+ Path);
		if(Path.equals("/role-add")) {
			doGetRoleAdd(req, resp);
		}else if (Path.equals("/role-table")) {
			doGetRoleTable(req, resp);
		}else if (Path.equals("/role-update")){
			doGetRoleUpdate(req, resp);
		}
	}
	
	
	
	private void doGetRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));		
		List<RoleEntity> list = roleService.getOneRole(id);
		req.setAttribute("role", list.get(0));
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
	}
	
//	private void doPostRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		int id = Integer.parseInt(req.getParameter("id"));	
//		String roleName = req.getParameter("roleName");
//		String roleDesc = req.getParameter("roleDesc");
//		boolean isSuccess = roleService.updateRole(roleName, roleDesc, id);
//		resp.sendRedirect(req.getContextPath() +"/role-table");
//	}
	
	private void doGetRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<RoleEntity> list = roleService.getAllRole();
		
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}
	
	private void doGetRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);

	}
	
//	private void doPostRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		String roleName = req.getParameter("role-name");
//		String roleDesc = req.getParameter("role-desc");
//		
//		boolean isSuccess = roleService.insert(roleName, roleDesc);
//		resp.sendRedirect(req.getContextPath() + "/role-table");
//	
//	}	
	
	
	
	
	
	
}
