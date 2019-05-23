package filter;



import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;








import service.Service;
import domain.User;

public class aotuloginfilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//强转成HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		
		User user = (User) req.getSession().getAttribute("user");
		
		if(user==null){
			String cookie_username = null;
			String cookie_password = null;
			
			//获取携带用户名和密码cookie
			Cookie[] cookies = req.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					//获得想要的cookie
					if("cookie_username".equals(cookie.getName())){
					
						cookie_username = URLDecoder.decode(cookie.getValue(), "UTF-8");
						System.out.println(cookie_username);
					}
					if("cookie_password".equals(cookie.getName())){
							
						cookie_password = URLDecoder.decode(cookie.getValue(), "UTF-8");
						System.out.println(cookie_password);
					}
				}
			}
			
			if(cookie_username!=null&&cookie_password!=null){
				//去数据库校验该用户名和密码是否正确
				Service service = new Service();
				try {
					user = service.finduser(cookie_username,cookie_password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				//完成自动登录 
				req.getSession().setAttribute("user", user);
				
			}
		
	}
		chain.doFilter(req, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
