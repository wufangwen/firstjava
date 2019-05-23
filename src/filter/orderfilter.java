package filter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;

public class orderfilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		System.out.println("dd");
		//校验用户是否登录----校验session是否存在user对象
		HttpSession session = req.getSession();

		//判断用户是否已经登录 未登录下面代码不执行
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		if(user==null){
			//没有登录
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
			return;
		}
		
		chain.doFilter(req, resp);
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}