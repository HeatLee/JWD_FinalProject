package by.training.finalproject.command.redirect;

import by.training.finalproject.command.Command;
import by.training.finalproject.command.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.INDEX.getValue();
    }
}
