package pl.sda.events.servlets;

import pl.sda.events.model.AppUser;
import pl.sda.events.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/event/list")
public class EventListServlet extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);
        if (httpSession != null && httpSession.getAttribute("logged_id") != null) {

            Long loggedUserId = Long.parseLong(httpSession.getAttribute("logged_id").toString());

            Optional<AppUser> appUserOptional = userService.findUserById(loggedUserId);
            if (appUserOptional.isPresent()) {
                req.setAttribute("eventList", appUserOptional.get().getEventList());

                req.getRequestDispatcher(req.getContextPath() + "/event/event_list.jsp").forward(req, resp);
                return;
            }

        }
        resp.sendRedirect("/login");
    }
}
