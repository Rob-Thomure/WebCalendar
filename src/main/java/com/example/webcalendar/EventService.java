package com.example.webcalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findByDate(LocalDate date) {
        return eventRepository.findEventByDate(date);

    }

    public Optional<Event> findById(long id) {
        return eventRepository.findById(id);
    }

    public void delete(long id) {
        eventRepository.deleteById(id);
    }

}
