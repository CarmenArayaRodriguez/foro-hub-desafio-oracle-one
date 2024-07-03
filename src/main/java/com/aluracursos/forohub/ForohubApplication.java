package com.aluracursos.forohub;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForohubApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRET", dotenv.get("jwt.secret"));
		System.setProperty("JWT_EXPIRATION", dotenv.get("jwt.expiration"));

		String jwtSecret = dotenv.get("jwt.secret");
		String jwtExpiration = dotenv.get("jwt.expiration");


		System.out.println("JWT_SECRET: " + jwtSecret);
		System.out.println("JWT_EXPIRATION: " + jwtExpiration);

		System.setProperty("jwt.secret", jwtSecret);
		System.setProperty("jwt.expiration", jwtExpiration);


		SpringApplication.run(ForohubApplication.class, args);

	}

	@Bean
	public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.setProtocol("org.apache.coyote.http11.Http11NioProtocol");
		return factory;
	}
}
