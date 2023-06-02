package pl.coderslab.servlets;

import com.mysql.cj.jdbc.exceptions.SQLError;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(name = "UserAdd", value = "/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/users/add.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        new UserDao().create(new User(request.getParameter("username"), request.getParameter("email"),
//                request.getParameter("password")));

        try {
            System.out.println("przed");
            new UserDao().create(new User(request.getParameter("username"), request.getParameter("email"),
                    request.getParameter("password")));
            response.setHeader("Refresh", "1; URL=/user/add");
            System.out.println("udało się");
        } catch (Exception e) {
            System.out.println("dupa");
            response.getWriter().println("Nie udało się dodać użytkownika, spróbuj ponownie.");
        }
    }
}