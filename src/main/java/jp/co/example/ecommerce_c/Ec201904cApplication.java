package jp.co.example.ecommerce_c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Ec201904cApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ec201904cApplication.class, args);
	}

}
