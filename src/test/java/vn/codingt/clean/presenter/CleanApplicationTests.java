package vn.codingt.clean.presenter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;

import static org.assertj.core.api.Assertions.assertThat;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CleanApplicationTests {

	private static String USER_EMAIL = "email@email.com";
	private static String USER_PASSWORD = "admin@123";
	private static final String USER_NAME = "name";
	private static final String USER_ADDRESS = "address";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate template;

	@Test
	@Sql(scripts = "classpath:clean-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void createCustomer() throws Exception {
		// When
		ResponseEntity<String> response = template.postForEntity(createUrl(ApiPath.API_CUSTOMER), createSignRequest(),
				String.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	@Sql(scripts = "classpath:clean-up.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void authenticateCustomer() throws Exception {
		// given
		template.postForEntity(createUrl(ApiPath.API_CUSTOMER), createSignRequest(), String.class);

		// When
		ResponseEntity<String> response = template.postForEntity(createUrl(ApiPath.API_CUSTOMER + "/auth"),
				createSignRequest(), String.class);
		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	private Object createSignRequest() {
		return new SignUpRequest(USER_NAME, USER_EMAIL, USER_ADDRESS, USER_PASSWORD);
	}

	private String createUrl(String path) throws Exception {
		return new URL(String.format("http://localhost:%s/%s", port, path)).toString();
	}

}
