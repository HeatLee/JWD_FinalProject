package by.training.finalproject.filter;

import by.training.finalproject.command.CommandParameter;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebFilter(filterName = "CommandCheckingFilter", urlPatterns = {"/controller"})
public class CommandCheckingFilter implements Filter {
    private static final Set<String> COMMANDS = Arrays.stream(CommandParameter.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String command = req.getParameter(JSPParameter.COMMAND.getValue());
        if (command == null || !COMMANDS.contains(command)) {
            req.getRequestDispatcher(Page.INDEX.getValue()).forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }
}
