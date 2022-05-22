package tech.kitucode.recommender.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.recommender.config.ApplicationProperties;
import tech.kitucode.recommender.domain.Event;
import tech.kitucode.recommender.repository.EventRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@Log4j2
public class EventService {

    private final EventRepository eventRepository;
    private final ApplicationProperties applicationProperties;

    public EventService(EventRepository eventRepository, ApplicationProperties applicationProperties) {
        this.eventRepository = eventRepository;
        this.applicationProperties = applicationProperties;
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


    /**
     * Process an event
     * Batch process since there are complex mathematical computations to be done
     */
    public void processEvents() {
        // need to start from last processed id

        int startId = getLastIdProcessed();
        log.info("Start processing of events from id : {}", startId);

        // update last processed id
    }

    /**
     * Get the last id processed from the config file
     * @return
     */
    public int getLastIdProcessed(){
        String configFileName = "id-tracker.txt";

        configFileName = applicationProperties.getConfigPath() + configFileName;

        File file = new File(configFileName);

        int startId = 0;
        try {
            Scanner reader = new Scanner(file);

            while(reader.hasNextInt()){
                startId = reader.nextInt();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return startId;
    }

    public void setLastIdProcessed(int lastIdProcessed){
        String configFileName = "id-tracker.txt";

        configFileName = applicationProperties.getConfigPath() + configFileName;

        File configFile = new File(configFileName);

        if(!configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(lastIdProcessed);
            fileWriter.close();
            log.info("Finished writing to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
