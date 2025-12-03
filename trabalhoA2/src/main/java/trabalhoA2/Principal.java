package trabalhoA2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Principal{

	@Autowired
	ResponsavelRepository responsavelRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Principal.class, args);
	}

}
