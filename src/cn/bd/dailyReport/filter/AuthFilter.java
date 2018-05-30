package cn.bd.dailyReport.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String currentURL = request.getRequestURI();  // 获取根目录所对应的绝对路径
		
		String targetURL = currentURL.substring(currentURL.indexOf("/", 1),  
                currentURL.length()); 
		HttpSession session = request.getSession();
		
		if(!"/login.jsp".equals(targetURL)){
			 if (session == null || session.getAttribute("loginer") == null) {  
	                // *用户登录以后需手动添加session  
	                System.out.println("request.getContextPath()="  
	                        + request.getContextPath());  
	                response.sendRedirect(request.getContextPath() + "/login.jsp");
	                // 如果session为空表示用户没有登录就重定向到login.jsp页面
	            } else {
				 chain.doFilter(request,response);
			 }
		}else{
			chain.doFilter(request,response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
