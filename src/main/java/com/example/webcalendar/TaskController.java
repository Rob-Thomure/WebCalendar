package com.example.webcalendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/event/today")
    public String[] getTodaysEvents() {
        return new String[0];
    }

}