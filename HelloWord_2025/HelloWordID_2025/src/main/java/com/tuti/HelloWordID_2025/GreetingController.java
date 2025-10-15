package com.tuti.HelloWordID_2025;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, User %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping("/greeting")
    public Greeting greeting(
        @RequestParam(value = "name", defaultValue = "Anonim") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}