package web.action.filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = {"/admin/*", "/CategoryServlet", "/ProductServlet"})
public class PriviilegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 获得session
        HttpServletRequest request = (HttpServletRequest) req;
        User user = (User) request.getSession().getAttribute("existUser");
        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
