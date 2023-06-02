package pl.coderslab.servlets;

import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = userDao.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/users/edit.jsp")
                .forward(request, response);

        if ("Edit".equals(request.getParameter("submit"))) {
            user.setUserName(request.getParameter("userName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            userDao.update(user);
            request.setAttribute("user", user);
//            response.sendRedirect(request.getContextPath() + "/users/edit.jsp");
//            response.sendRedirect(request.getContextPath() + "/user/edit?id=" + request.getParameter("id") + "&userName="
//                    + request.getParameter("userName") + "&email=" + request.getParameter("email") + "&password=" + request.getParameter("password") + "&submit=Edit");
//            response.setHeader("Refresh", "1; URL=/user/edit?id=" + request.getParameter("id") + "&userName="
//                    + request.getParameter("userName") + "&email=" + request.getParameter("email") + "&password=" + request.getParameter("password") + "&submit=Edit");
            //change parameter submit to null
//            response.sendRedirect(request.getContextPath() + "/user/edit?id=" + request.getParameter("id") + "&userName=" + request.getParameter("userName") + "&email=" + request.getParameter("email") + "&password=" + request.getParameter("password") + "&submit=");
            getServletContext()
                    .getRequestDispatcher("/users/edit.jsp")
                    .forward(request, response);
            response.setHeader("Refresh", "1; URL=/user/edit?id=" + request.getParameter("id")
                    + "&userName=" + request.getParameter("userName") + "&email=" +
                    request.getParameter("email") + "&password=" + request.getParameter("password") +
                    "&submit=Edit");
        }
    }
}