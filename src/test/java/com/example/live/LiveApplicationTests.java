package com.example.live;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class LiveApplicationTests {
	@Test
	void contextLoads() {
		// This method will pass if the application context loads successfully
	}

}
