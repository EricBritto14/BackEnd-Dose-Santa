package santaDose.voll.apiBack.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Classe para configurara o pedimento de token para fazer as requisições na API
@Configuration
public class SpringDoConfigurations {

    //@Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Santa.Dose API")
                        .description("API Rest da Adega Santa Dose, contendo as funcionalidades de CRUD de produtos, variedades e clientes, além de agendamento e cancelamento de pedidos")
                        .contact(new Contact()
                                .name("Backend")
                                .email("SantaDose@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://Santa.dose/api/licensa")));

    }
}
