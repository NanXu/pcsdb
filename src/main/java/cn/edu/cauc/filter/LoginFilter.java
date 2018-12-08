package cn.edu.cauc.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cauc.model.vo.SessionUser;
import cn.edu.cauc.util.Constant;

/**
 * 作者：徐楠
 *
 * 描述：<p>系统登录过滤器</p>
 * 创建时间：2016年2月15日
 */
public class LoginFilter implements Filter {
	
	private static final List<String> excludedPages = new ArrayList<String>();
	{
		excludedPages.add("/pcsdb/");
		excludedPages.add("/pcsdb/toLogin");//根目录
		excludedPages.add("/pcsdb/WEB-INF/jsp/login.jsp");
	};
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri = request.getRequestURI();
		String url = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		int i = url.indexOf(contextPath);
		String host = url.substring(0, i);
		//uri = uri.substring(contextPath.length());
		if(!excludedPages.contains(uri)) {
			HttpSession session = request.getSession();
			SessionUser sessionUser = (SessionUser)session.getAttribute(Constant.SESSION_USER);
			if(sessionUser == null) {
				String loginPath = host + contextPath + Constant.LOGIN_URL;
				response.sendRedirect(loginPath);
				return;
			}
		}
		//chain.doFilter(servletRequest, servletResponse);
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
