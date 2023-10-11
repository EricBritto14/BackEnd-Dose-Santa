package santaDose.voll.apiBack.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Para indicar que é uma classe de configurações para o Spring
@EnableWebSecurity //Para indicar que são personalizações de segurança
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    //@Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers( "/v3/api-docs/**", "/swagger-ui.html",  "/swagger-ui/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/produtos").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //Codigo para dizer para o Spring que o meu filtro de validação deve vir primeiro que o dele, se não da b.o
                .build();


    }

    //Esse throws Exception, você só vai usar quando um metodo exige um retorno de exception, como por exemplo esse configuration.getAuthenticationManager()
    //Como ele requer um retorno de exception e eu não vou retornar uma, e sim uma função, então tem que colocar esse throws Exception
    @Bean //@Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga carregá-la e realize a sua injeção de dependência em outras classes
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager(); //Ensinando o Spring como injetar automaticamente o AuthenticationManager da classe do Controller
        //Sem esse comando ele não sabe injetar sozinho e da erro.
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //Ensinando e dizendo para o Spring que o projeto está usando um hashing de senha, para na hora do cadastro não
        //ficar aberto a senha lá, caso o bd sejá envadido
        return new BCryptPasswordEncoder();
    }
}
