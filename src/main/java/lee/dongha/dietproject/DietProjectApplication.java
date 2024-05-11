package lee.dongha.dietproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DietProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DietProjectApplication.class, args);
	}

}
