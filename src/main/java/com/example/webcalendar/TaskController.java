package com.example.webcalendar;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/event/today")
    public ResponseEntity<?> getTodayEvents() {
        List<Event> events = eventService.findByDate(LocalDate.now());
        return ResponseEntity.ok().body(events);
    }

    @GetMapping(path = "/event/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") long id) {
        Event event = eventService.findById(id).orElseThrow(() -> new NoSuchElementException("The event doesn't exist!"));
        return ResponseEntity.ok(event);
    }

    @DeleteMapping(path = "/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") long id) {
        Event event = eventService.findById(id).orElseThrow(() -> new NoSuchElementException("The event doesn't exist!"));
        eventService.delete(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping(path = "/event")
    public ResponseEntity<?> getEventsByDate(@RequestParam(value = "start_time", required = false) LocalDate startTime,
                                             @RequestParam(value = "end_time", required = false) LocalDate endTime) {
        List<Event> events = eventService.findAll();
        List<Event> filteredEvents = new ArrayList<>();
        if (null != startTime && null != endTime) {
            filteredEvents = events.stream().filter(event ->
                            event.getDate().isEqual(startTime) ||
                                    event.getDate().isEqual(endTime) ||
                                    (startTime.isBefore(event.getDate()) && endTime.isAfter(event.getDate())))
                    .toList();
        }
        return filteredEvents.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(filteredEvents);
    }

}
