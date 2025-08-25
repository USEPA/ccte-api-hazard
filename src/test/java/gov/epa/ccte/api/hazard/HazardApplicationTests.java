package gov.epa.ccte.api.hazard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HazardApplicationTests {
    
	@Test
	void contextLoads() {
	}

	@Test
	public void applicationStarts(){
		assertThatCode(() -> HazardApplication.main(new String[]{})).doesNotThrowAnyException();
	}
}
