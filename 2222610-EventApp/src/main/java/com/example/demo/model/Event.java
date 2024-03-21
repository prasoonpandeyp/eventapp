package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "events")
@Data
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    private Long id;

    private String eventName;
    private String eventDT;
    private String eventDescr;
    private boolean isBooked;
    private String eventLocation;

    public Event() {
        // Empty constructor
    }


}
