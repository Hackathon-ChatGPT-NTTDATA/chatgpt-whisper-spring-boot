package hackathon.nttdata.coderpath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SingularidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingularidadApplication.class, args);
	}

}
