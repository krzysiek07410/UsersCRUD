package pl.coderslab.servlets;

import pl.coderslab.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserRemove", value = "/user/remove")
public class UserRemove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", new UserDao().read(Integer.parseInt(request.getParameter("id"))));

        getServletContext()
                .getRequestDispatcher("/users/confirmRemove.jsp")
                .forward(request, response);
    }
}