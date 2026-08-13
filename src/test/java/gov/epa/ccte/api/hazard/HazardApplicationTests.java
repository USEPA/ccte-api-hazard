package gov.epa.ccte.api.hazard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=io.sentry.spring.boot.jakarta.SentryAutoConfiguration"
})
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HazardApplicationTests {
    
	@Test
	void contextLoads() {
	}

}