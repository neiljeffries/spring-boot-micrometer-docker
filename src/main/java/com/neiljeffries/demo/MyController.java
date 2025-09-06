package com.neiljeffries.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyController {

    private final MyService testService;

    public MyController(MyService testService) {
        this.testService = testService;
    }

    @GetMapping
    public MyResponse test() {
        return testService.getTestMessage();
    }
}
