package in.nkn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringSecurityConsumerApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringSecurityConsumerApplication.class, args);
		

		String apiUrl = "http://localhost:9090/api/security/transfer";

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("vivek", "vivek@gmail.com");

		HttpEntity<String> reqEntity = new HttpEntity<>(headers);

		RestTemplate rt = new RestTemplate();

		ResponseEntity<String> resEntity =
		        rt.exchange(apiUrl, HttpMethod.GET, reqEntity, String.class);

		System.out.println(resEntity.getBody());
		
	}

}
