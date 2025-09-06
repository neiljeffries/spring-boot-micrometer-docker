package com.neiljeffries.demo;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class MyService {
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_INSTANT;

    private final Counter testRequestsCounter;
    private final Timer testRequestTimer;

    public MyService(MeterRegistry meterRegistry) {
        this.testRequestsCounter = Counter.builder("test_service_requests_total")
                .description("Total requests to TestService#getTestMessage")
                // service tag supplied globally
                .register(meterRegistry);
        this.testRequestTimer = Timer.builder("test_service_request_duration")
                .description("Duration of TestService#getTestMessage executions")
                .publishPercentileHistogram()
                .register(meterRegistry);
    }

    public MyResponse getTestMessage() {
        Supplier<MyResponse> supplier = () -> {
            testRequestsCounter.increment();
            String ts = ISO_FORMATTER.format(Instant.now());
            return new MyResponse(ts, "You da man!");
        };
        return testRequestTimer.record(supplier);
    }
}
