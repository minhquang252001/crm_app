package testservlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testservlet.Service.LoginService;
import testservlet.payload.BasicResponse;

@WebServlet(name ="loginController", urlPatterns = {"/login","/login-add"})
public class LoginController extends HttpServlet {

		private LoginService loginService = new LoginService();
		private Gson gson = new Gson();
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("remember");
			req.setAttribute("email",email);
			req.setAttribute("password",password);
			boolean isSuccess = loginService.checkLogin(email, password, remember, resp);
			System.out.println("Kiểm tra login " + isSuccess);
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			boolean isSuccess = loginService.getInserLogin(email, password, name);
			System.out.println("kiểm trả doPost Login " + isSuccess);
			System.out.println("kiểm trả doPost Login " + email);
			System.out.println("kiểm trả doPost Login " + password);
			System.out.println("kiểm trả doPost Login " + name);

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
}
