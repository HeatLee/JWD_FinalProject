package by.training.finalproject.filter;


import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.CommandParameter;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;

@WebFilter(filterName = "AdminSessionFilter", urlPatterns = {"/controller"})
public class AdminSessionFilter implements Filter {
    private static final EnumSet<CommandParameter> ADMIN_COMMANDS =
            EnumSet.range(CommandParameter.ADMIN_PAGE, CommandParameter.ADD_ROOM);

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        CommandParameter command = CommandParameter.valueOf(req.getParameter(JSPParameter.COMMAND.getValue()));
        User user = (User) session.getAttribute(Attribute.USER.getAttribute());
        if (ADMIN_COMMANDS.contains(command) && (user == null || user.getUserRole() != UserRole.ADMIN)) {
            req.getRequestDispatcher(Page.SIGN_IN.getValue()).forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }
}
