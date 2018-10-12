package pl.sda.events.api;


import pl.sda.events.model.Event;
import pl.sda.events.model.dto.EventDto;
import pl.sda.events.service.EventService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/event/add")
public class EventAddApiController {

    @EJB
    private EventService eventService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewEvent(EventDto eventDto){
        Optional<Event> createdOptional = eventService.addNewEvent(eventDto);
        if (createdOptional.isPresent()){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
