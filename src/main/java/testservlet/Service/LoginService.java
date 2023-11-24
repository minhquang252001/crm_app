package testservlet.Service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import testservlet.entity.UserEntity;
import testservlet.repository.UserRepository;

public class LoginService {
	private UserRepository userRepository = new UserRepository();
	
	public boolean checkLogin(String email, String password,String remember, HttpServletResponse resp) {
		List<UserEntity> list = userRepository.findByEmailAndPassword(email, password);
		boolean isSucces = list.size()>0;
		if (isSucces && remember !=null) {
			Cookie cookie = new Cookie("email",email);
			Cookie cookie2 = new Cookie("password",password);
			cookie.setMaxAge(2*60*60);
			cookie2.setMaxAge(2*60*60);
			
			resp.addCookie(cookie);
			resp.addCookie(cookie2);
		}
	return isSucces;
	}
	public boolean getInserLogin(String email,String password,String name) {
		return userRepository.inserUser(email, password, name) >0;
	}
}
