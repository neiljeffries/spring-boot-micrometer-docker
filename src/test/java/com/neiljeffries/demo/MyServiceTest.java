package com.neiljeffries.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

class MyServiceTest {

    private MeterRegistry registry;
    private MyService service;

    @BeforeEach
    void setUp() {
        registry = new SimpleMeterRegistry();
        service = new MyService(registry);
    }

    @Test
    void getTestMessage_returnsExpectedResponse_andRecordsMetrics() {
        MyResponse response = service.getTestMessage();

        assertThat(response).isNotNull();
        assertThat(response.message()).isEqualTo("You da man!");
        // Timestamp should be ISO-8601 instant parseable
        Instant parsed = Instant.parse(response.timestamp());
        assertThat(parsed).isNotNull();

        // Counter incremented once
        double count = registry.counter("test_service_requests_total").count();
        assertThat(count).isEqualTo(1.0);

        // Timer recorded once
        Timer timer = registry.find("test_service_request_duration").timer();
        assertThat(timer).isNotNull();
        assertThat(timer.count()).isEqualTo(1);
    }
}
