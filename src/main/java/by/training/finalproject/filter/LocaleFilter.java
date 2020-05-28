package by.training.finalproject.filter;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final String ENGLISH = "en";


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        if (session.getAttribute(Attribute.LANGUAGE.getAttribute()) == null) {
            session.setAttribute(Attribute.LANGUAGE.getAttribute(), ENGLISH);
        }
        String language = req.getParameter(JSPParameter.LANGUAGE.getValue());
        if (language != null) {
            session.setAttribute(Attribute.LANGUAGE.getAttribute(), new Locale(language));
            req.getRequestDispatcher(Page.INDEX.getValue()).forward(req, resp);
            return;
        }
        chain.doFilter(request, response);

    }
}
