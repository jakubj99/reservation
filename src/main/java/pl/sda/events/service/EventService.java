package pl.sda.events.service;


import pl.sda.events.dao.EventDao;
import pl.sda.events.model.AppUser;
import pl.sda.events.model.Event;
import pl.sda.events.model.dto.EventDto;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@LocalBean
@Singleton
public class EventService {

    @EJB
    private EventDao eventDao;

    @EJB
    private UserService userService;


    public void save(Event event) {
        eventDao.save(event);
    }

    public List<Event> getEventList(){
        return eventDao.getAllEvents();
    }

    public Optional<Event> addNewEvent(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setTime(LocalDateTime.parse(eventDto.getTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        event.setLenght(eventDto.getLength());
        event.setDescription(eventDto.getDescription());

        Long owner_id = Long.parseLong(eventDto.getOwnerId());

        Optional<AppUser> appUserOptional = userService.findUserById(owner_id);
        if (appUserOptional.isPresent()){
            event.setOwner(appUserOptional.get());
            eventDao.save(event);
            return Optional.of(event);
        }

        return Optional.empty();
    }
}
