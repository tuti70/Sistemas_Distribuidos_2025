package com.tuti.HelloWorld_2025;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String Hello() {
        return "Hello, World! teste";
    }
}
