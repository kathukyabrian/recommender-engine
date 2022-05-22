package tech.kitucode.recommender.web.rest;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.recommender.domain.Event;
import tech.kitucode.recommender.service.EventService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class EventResource {

    private final EventService eventService;
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public Event save(@RequestBody Event event) {
        log.debug("REST request to save event : {}", event);

        return eventService.save(event);
    }

    @PutMapping("/events")
    public Event update(@RequestBody Event event) {
        log.debug("REST request to update event : {}", event);

        return eventService.update(event);
    }

    @GetMapping("/events/{id}")
    public Event getOne(@PathVariable Long id) {
        log.debug("REST request to find event with id : {}", id);

        return eventService.findOne(id);
    }

    @PostMapping("/events/bulk-save")
    public List<Event> saveMany(@RequestBody List<Event> events) {
        log.debug("REST request to save many events");

        return eventService.saveMany(events);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        log.debug("REST request to delete event with id : {}", id);

        eventService.deleteEvent(id);
    }

    @GetMapping("/events/process")
    public void processEvents(){
        log.debug("REST request to process events");

        eventService.processEvents();;
    }
}
