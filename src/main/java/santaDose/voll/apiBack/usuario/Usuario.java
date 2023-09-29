package santaDose.voll.apiBack.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//Criando uma JPA assim como as outras classem q não são DTO, para o cadastro do usuario e assim entrar na API para pegar as informações
@Table(name = "usuarios") // Entidade e tabela dos bancos de dados
@Entity(name = "Usuario")
@Getter //Criando todos os getters sem precisar codar
@NoArgsConstructor
//Criando construtor que não precisa de argumento  (Default que a JPA exige em todas a entidade) JPA = Java Persistence API
@AllArgsConstructor //Criando um cronstrutor que requer os argumentos
@EqualsAndHashCode(of = "id") //Gerar o EqualsAndHashCOde em cima do ID não de todos os outros
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gerar um valor unico em cima do ID
    private Long id;
    private String login;
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Override de controle de perfis, no caso, administrador, usuario, moderador e etc
        //Para funcionar aqui, tem que criar uma classe que representa esses perfis especificos (fazer depois)
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
