package com.example.webcalendar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class Event {

    String message;

    @NotEmpty @NotBlank
    String event;

    @NotNull
    LocalDate date;

    public String getMessage() {
        return message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.message = "The event has been added!";
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return """
                "message": "The event has been added!",
                "event": "%s",
                "date": "%s"
                """.formatted(event, date);
    }
}
