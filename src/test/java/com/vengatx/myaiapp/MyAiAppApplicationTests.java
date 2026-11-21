package com.vengatx.myaiapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.ai.chat.memory.repository.jdbc.initialize-schema=never")
class MyAiAppApplicationTests {

	@Test
	void contextLoads() {
	}

}
