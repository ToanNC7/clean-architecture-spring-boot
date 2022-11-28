package vn.codingt.clean.presenter;

import vn.codingt.clean.presenter.usecases.secutiry.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan({ "vn.codingt.clean.presenter", "vn.codingt.clean.data.db.jpa" })
public class CleanApplication {

	@Autowired
	private JwtProvider tokenProvider;

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CleanApplication.class, args);
	}

	public class CustomHttpRequestInterceptor implements ClientHttpRequestInterceptor{
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
			HttpHeaders httpHeaders = request.getHeaders();
			httpHeaders.put(HttpHeaders.AUTHORIZATION, Arrays.asList(tokenProvider.getJwtSecret()));
			return execution.execute(request, body);
		}
	}

}
