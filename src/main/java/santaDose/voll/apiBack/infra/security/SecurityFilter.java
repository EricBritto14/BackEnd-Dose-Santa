package santaDose.voll.apiBack.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import santaDose.voll.apiBack.usuario.UsuarioRepository;

import java.io.IOException;

@Component //Component para mostrar que a classe é um componente generico, e só para o Spring rodar essa classe automaticamente
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository; //Criando essa variavel para entrar no banco de dados do usuario, e pegar os valores do seu objeto
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null){
            var subject = tokenService.getSubject((String) tokenJWT); // Para recuperar o token do cabeçalho do insomnia, e se tiver, guardar em uma variável e fazer o teste, se não ele segue o fluxo
            var usuario = repository.findByLogin(subject); //Passando o subject pois foi onde guardou as informações do usuario, codigo para recuperar que usuario está fazendo login e fazendo os requisitos futuramente

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //UserNamePasswordAuthenticationToken seria uma classe DTO do prórpio usuario, seria para guardar e passar na linha de baixo, quem é e suas autorizações
            SecurityContextHolder.getContext().setAuthentication(authentication); //Codigo para fazer a autenticação de quem está logado e fazendo requisições
        }


        filterChain.doFilter(request, response); //Para passar de filtro para filtro, e assim retornar o json com os valores, não apenas um ok
    }


    private Object recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
