package santaDose.voll.apiBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Em ApplicationProperties
//spring datasource username e password seria para passar o nome do banco de dados e a senha cadastrada
//spring datasource seria para passar a url de conexão com o banco de dados da maquina



@SpringBootApplication
public class  ApiBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBackApplication.class, args);

	}
}
