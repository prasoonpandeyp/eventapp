package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//mark this class as Rest Controller and request mapping is /event-management
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseMessage;
import com.example.demo.service.EventService;

@RestController("/event-management")
public class EventController {

    //implement logger mechanism
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    @Autowired
    private EventService eventService;
    
    //create a method to get all events
    public ResponseMessage getAllEvents() {
        logger.info("Retrieving all events");
        return eventService.getAllEvents();
    }
    //create a method to get all events by descing order
    public ResponseMessage getAllEventsByDesc() {
        logger.info("Retrieving all events by descending order");
        return eventService.getAllEventsByOrder();
    }

    //create a method to get event by id
    public ResponseMessage getEventById(int id) {
        logger.info("Retrieving event by id");
        return eventService.getEventById(id);
    }
}
