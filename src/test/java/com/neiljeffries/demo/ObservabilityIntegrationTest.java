package com.neiljeffries.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ObservabilityIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void health_and_info_exposed() throws Exception {
        mockMvc.perform(get("/actuator/health")).andExpect(status().isOk());
        mockMvc.perform(get("/actuator/info")).andExpect(status().isOk());
    }

    @Test
    void metrics_increment_after_request() throws Exception {
        mockMvc.perform(get("/test")).andExpect(status().isOk())
              .andExpect(jsonPath("$.message", is("You da man!")));

        mockMvc.perform(get("/actuator/metrics/test_service_requests_total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test_service_requests_total")));

        mockMvc.perform(get("/actuator/metrics/test_service_request_duration"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test_service_request_duration")));
    }
}
