package tech.kitucode.recommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tech.kitucode.recommender.config.ApplicationProperties;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class RecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommenderApplication.class, args);
	}

}
