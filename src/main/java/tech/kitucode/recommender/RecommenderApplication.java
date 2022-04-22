package tech.kitucode.recommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tech.kitucode.recommender.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class RecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommenderApplication.class, args);
	}

}
