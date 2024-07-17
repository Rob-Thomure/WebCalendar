package com.example.webcalendar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty @NotBlank
    private String event;

    @NotNull
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotEmpty @NotBlank String getEvent() {
        return event;
    }

    public void setEvent(@NotEmpty @NotBlank String event) {
        this.event = event;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", event='" + event + '\'' +
                ", date=" + date +
                '}';
    }
}
