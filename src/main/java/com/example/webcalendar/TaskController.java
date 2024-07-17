package com.example.webcalendar;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {
    private final EventService eventService;

    @Autowired
    public TaskController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event) {
        eventService.save(event);
        return ResponseEntity.ok().body("""
                {
                    "message": "The event has been added!",
                    "event": "%s",
                    "date": "%s"
                }""".formatted(event.getEvent(), event.getDate()));
    }

    @GetMapping("/event")
    public ResponseEntity<?> getAllEvents() {
        List<Event> events = eventService.findAll();
        return events.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(events);
    }

    @GetMapping("/event/today")
    public ResponseEntity<?> getTodaysEvents() {
        List<Event> events = eventService.findByDate(LocalDate.now());
        return ResponseEntity.ok().body(events);
    }

}
