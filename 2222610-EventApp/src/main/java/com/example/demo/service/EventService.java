package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Event;
import com.example.demo.model.ResponseMessage;
import com.example.demo.repo.EventRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EventService {
    private static final Logger LOGGER = Logger.getLogger(EventService.class.getName());

    @Autowired
    private EventRepository eventRepository;

    public ResponseMessage createEvent(Event event) {
        try {
            Event exist = eventRepository.findByEventName(event.getEventName());
            if (exist != null) {
                LOGGER.warning("Event already exists");
                return new ResponseMessage("Error", "Event already exists", null);
            } else {
                exist = eventRepository.save(event);
                LOGGER.info("Event successfully saved");
                return new ResponseMessage("INFO", "Event Successfully Saved !", exist);
            }
        } catch (Exception e) {
            LOGGER.severe("Error occurred: " + e.getMessage());
            return new ResponseMessage("Error", e.getMessage(), null);
        }
    }
    public ResponseMessage getAllEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            LOGGER.info("Retrieved all events");
            return new ResponseMessage("INFO", "Retrieved all events", events);
        } catch (Exception e) {
            LOGGER.severe("Error occurred: " + e.getMessage());
            return new ResponseMessage("Error", e.getMessage(), null);
        }
    }

    //create a method to get event by Id
    public ResponseMessage getEventById(long id) {
        try {
            Event event = eventRepository.findById(id).orElse(null);
            if (event == null) {
                LOGGER.warning("Event not found");
                return new ResponseMessage("Error", "Event not found", null);
            } else {
                LOGGER.info("Retrieved event by id");
                return new ResponseMessage("INFO", "Retrieved event by id", event);
            }
        } catch (Exception e) {
            LOGGER.severe("Error occurred: " + e.getMessage());
            return new ResponseMessage("Error", e.getMessage(), null);
        }
    }
    //create a method getting all event by desciding order by event date time
    public ResponseMessage getAllEventsByOrder() {
        try {
            List<Event> events = eventRepository.findAllByOrderByEventDTDesc();
            LOGGER.info("Retrieved all events by order");
            return new ResponseMessage("INFO", "Retrieved all events by order", events);
        } catch (Exception e) {
            LOGGER.severe("Error occurred: " + e.getMessage());
            return new ResponseMessage("Error", e.getMessage(), null);
        }
    }
}
