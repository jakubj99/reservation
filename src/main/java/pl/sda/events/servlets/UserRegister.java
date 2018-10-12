package pl.sda.events.servlets;


import pl.sda.events.model.AppUser;
import pl.sda.events.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user/register")
public class UserRegister extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/user/user_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!validatePassword(req.getParameter("password"), req.getParameter("password-confirm"))) {
            req.setAttribute("error_message", "Password do not match or are empty!");
            req.getRequestDispatcher(req.getContextPath() + "/user/user_form.jsp").forward(req, resp);
            return;
        }

        if (!validateEmail(req.getParameter("email"))) {
            req.setAttribute("error_message", "This email is already registered!");

            req.getRequestDispatcher(req.getContextPath() + "/user/user_form.jsp").forward(req, resp);
            return;
        }

        if (!validateEmail(req.getParameter("username"))) {
            req.setAttribute("error_message", "This username is already registered!");

            req.getRequestDispatcher(req.getContextPath() + "/user/user_form.jsp").forward(req, resp);
            return;
        }

        AppUser appUser = new AppUser();
        appUser.setEmail(req.getParameter("email"));
        appUser.setPassword(req.getParameter("password"));
        appUser.setUsername(req.getParameter("username"));

        userService.register(appUser);

        resp.sendRedirect(req.getContextPath() + "/");
    }

    private boolean validateUsername(String username) {
        if (!username.isEmpty() && userService.checkUsernameNotExist(username)) {
            return true;
        }
        return false;
    }

    private boolean validateEmail(String email) {
        if (!email.isEmpty() && userService.checkEmailNotExist(email)) {
            return true;
        }
        return false;
    }

    private boolean validatePassword(String password, String confirm) {
        if (!password.isEmpty() && password.equals(confirm)) {
            return true;
        }
        return false;
    }
}
