package com.neiljeffries.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class MyController {

    private final MyService testService;

    public MyController(MyService testService) {
        this.testService = testService;
    }

    @GetMapping
    public MyResponse test() {
        log.info("Received request at /test endpoint");
        return testService.getTestMessage();
    }
}
