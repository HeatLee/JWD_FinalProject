package by.training.finalproject.filter;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.CommandParameter;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

@WebFilter(filterName = "UserSessionFilter", urlPatterns = {"/controller"})
public class UserSessionFilter implements Filter {
    private static final EnumSet<CommandParameter> USER_COMMANDS =
            EnumSet.range(CommandParameter.SIGN_OUT, CommandParameter.GET_USER_RESPONSE);

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        CommandParameter command = CommandParameter.valueOf(req.getParameter(JSPParameter.COMMAND.getValue()));
        User user = (User) session.getAttribute(Attribute.USER.getAttribute());
        if (USER_COMMANDS.contains(command) && user == null) {
            req.getRequestDispatcher(Page.SIGN_IN.getValue()).forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }
}
