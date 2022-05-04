package tech.kitucode.recommender.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.recommender.domain.Event;
import tech.kitucode.recommender.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Save an event
     *
     * @param event
     * @return
     */
    public Event save(Event event) {
        log.debug("Request to save event : {}", event);

        // extract application id info

        // global user id config

        event.setIsProcessed(Boolean.FALSE);
        event.setCreatedOn(LocalDateTime.now());

        event = eventRepository.save(event);
        return event;
    }

    /**
     * Update an event
     *
     * @param event
     * @return
     */
    public Event update(Event event) {
        log.debug("Request to update event with id : {}", event.getId());

        return event;
    }

    /**
     * Find an event by eventId
     *
     * @param id
     * @return
     */
    public Event findOne(Long id) {

        log.debug("Getting event with id : {}", id);

        Event event = null;

        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isPresent()) {
            event = optionalEvent.get();
        }

        return event;
    }

    /**
     * Find events by application id
     *
     * @param applicationId
     * @return
     */
    public List<Event> findByApplication(Long applicationId, Integer pageSize, Integer pageNo) {

        log.debug("Request to get events by application : {}", applicationId);
        
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Event> pagedEvents = eventRepository.findAllByApplicationId(applicationId, pageable);

        List<Event> events = new ArrayList<>();
        if (pagedEvents.hasContent()) {
            events = pagedEvents.getContent();
            return events;
        }

        return events;
    }

    /**
     * Find events by global user id
     *
     * @param globalUserId
     * @return
     */
    public List<Event> findByUser(Long globalUserId) {
        List<Event> eventList = new ArrayList<>();

        return eventList;
    }

    /**
     * Delete an event by id
     *
     * @param id
     */
    public void deleteEvent(Long id) {
        log.debug("Request to delete event with id : {}", id);

        eventRepository.deleteById(id);
    }

    /**
     * Save many events
     *
     * @param eventList
     * @return
     */
    public List<Event> saveMany(List<Event> eventList) {
        log.debug("Request to save {} events", eventList.size());

        List<Event> savedEvents = eventRepository.saveAll(eventList);

        return savedEvents;
    }

}
