package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Event;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, Long>{

    //create a method to list all events by descending order of date
    List<Event> findAllByOrderByEventDTDesc();
    //create a method to find event by event name
    Event findByEventName(String eventName);
}
