package flightms.module1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


//@EnableSwagger2
@SpringBootApplication
@EnableWebMvc
public class Module1Application {

	public static void main(String[] args) {
	
		SpringApplication.run(Module1Application.class, args);
	}

}
