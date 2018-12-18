package cn.edu.cauc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getHeader("X-Requested-With") != null && req.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            request.setCharacterEncoding("utf-8");
        } else {
            request.setCharacterEncoding("gbk");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
