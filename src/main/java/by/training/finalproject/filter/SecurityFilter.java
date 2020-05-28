package by.training.finalproject.filter;

import by.training.finalproject.command.JSPParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PageRedirectSecurityFilter", urlPatterns = {"/page/*"})
public class SecurityFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getParameter(JSPParameter.COMMAND.getValue()) == null) {
            resp.sendRedirect(req.getContextPath() + "/controller?command=INDEX_PAGE");
        }
        chain.doFilter(request, response);
    }
}
