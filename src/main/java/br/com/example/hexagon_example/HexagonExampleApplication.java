package br.com.example.hexagon_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HexagonExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonExampleApplication.class, args);
	}

}
