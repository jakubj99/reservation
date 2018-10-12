package pl.sda.events.servlets;

import pl.sda.events.model.AppUser;
import pl.sda.events.model.Event;
import pl.sda.events.service.EventService;
import pl.sda.events.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@WebServlet("/event/add")
public class EventAddServlet extends HttpServlet {

    @EJB
    private UserService userService;

    @EJB
    private EventService eventService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("logged_id") == null){
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("/event/event_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String length = req.getParameter("length");
        String time = req.getParameter("time");
        String owner_id = req.getParameter("owner_id");

        Event event = new Event();
        event.setDescription(description);
        event.setLenght(Integer.parseInt(length));
        event.setName(name);
        event.setTime(LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

        Optional<AppUser> appUserOptional = userService.findUserById(Long.parseLong(owner_id));
        if (appUserOptional.isPresent()){

            AppUser owner = appUserOptional.get();

            event.setOwner(owner);

            owner.getEventList().add(event);

            eventService.save(event);

//            userService.update(owner);
        }

        resp.sendRedirect(  req.getContextPath() + "/event/list");
    }


}
