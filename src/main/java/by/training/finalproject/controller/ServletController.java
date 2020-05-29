package by.training.finalproject.controller;

import by.training.finalproject.command.Attribute;
import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
import by.training.finalproject.command.Page;
import by.training.finalproject.factory.CommandFactory;
import by.training.finalproject.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ServletController extends HttpServlet {
    private static final CommandFactory COMMAND_FACTORY = new CommandFactory();
    private static final String URL = "%s%s?command=%s";

    @Override
    public void init() throws ServletException {
        ConnectionPool.INSTANCE.initConnectionPool();
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(processRequest(req, resp)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextPage = processRequest(req, resp);
        String next = Page.getCommandNameByPath(nextPage);
        if (next.equals(Page.ERROR_500_JSP.getCommandName())) {
            resp.sendError(Integer.parseInt(next));
        } else {
            if (req.getAttribute(Attribute.ERR_PARAMETER.getAttribute()) != null) {
                req.getRequestDispatcher(nextPage).forward(req, resp);
            } else {
                resp.sendRedirect(String.format(URL, req.getContextPath(), req.getServletPath(), next));
            }
        }
    }

    private String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = COMMAND_FACTORY.getCommand(request.getParameter(JSPParameter.COMMAND.getValue()));
        return command.execute(request, response);
    }
}
