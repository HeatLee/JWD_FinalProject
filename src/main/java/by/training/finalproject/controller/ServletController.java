package by.training.finalproject.controller;

import by.training.finalproject.command.Command;
import by.training.finalproject.command.JSPParameter;
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

    @Override
    public void init() throws ServletException {
        ConnectionPool.INSTANCE.initConnectionPool();
    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.destroyPool();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = COMMAND_FACTORY.getCommand(request.getParameter(JSPParameter.COMMAND.getValue()));
        String path = command.execute(request, response);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
