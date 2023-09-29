package santaDose.voll.apiBack.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Metodo para fazer a consulta do usuario no banco de dados
    UserDetails findByLogin(String login);
}
