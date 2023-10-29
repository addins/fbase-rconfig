package org.addin.learn.fbase.rconfig;

import org.addin.learn.fbase.rconfig.config.FirebaseOverrideConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
		FbaseRconfigApplication.class,
		FirebaseOverrideConfiguration.class,
}, properties = {"spring.main.allow-bean-definition-overriding=true"})
class FbaseRconfigApplicationTests {

	@Test
	void contextLoads() {
	}

}
