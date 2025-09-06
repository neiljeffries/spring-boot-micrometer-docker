package com.neiljeffries.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class MyControllerTest {

    @Test
    void test_returnsServiceResponse() {
        MyService myService = mock(MyService.class);
        when(myService.getTestMessage()).thenReturn(new MyResponse("2024-01-01T00:00:00Z", "You da man!"));

        MyController controller = new MyController(myService);

        MyResponse resp = controller.test();
        assertThat(resp.message()).isEqualTo("You da man!");
        assertThat(resp.timestamp()).isEqualTo("2024-01-01T00:00:00Z");
    }
}
