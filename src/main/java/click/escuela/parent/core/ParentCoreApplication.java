package click.escuela.parent.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ParentCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParentCoreApplication.class, args);
	}

}
