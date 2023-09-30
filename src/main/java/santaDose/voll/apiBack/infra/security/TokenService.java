package santaDose.voll.apiBack.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import santaDose.voll.apiBack.usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service //Para mostrar para o spring q essa classe vai gerar um serviço, e assim ele rodar a classe automaticamente
public class TokenService  {
    @Value("${api.security.token.secret}") //Dizendo que o valor da variavel secret vem lá do application properties desta chave
    private String secret;

    //Passando a classe do Usuario, para ser possivel relacionar o token a um usuario especifico
    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret); //Algorith é a biblioteca que vamos usar para fazer a assinatura digital do token
            return JWT.create()
                    .withIssuer("API Adega Santa Dose") //Quem ta gerando, para identificar quem ta gerando o TOKEN... normalmente coloca o nome da aplicação mesmo
                    .withSubject(usuario.getLogin()) //Quem é a pessoa relacionada com esse token
                    .withClaim("id", usuario.getId()) //Passando quem é o id do usuario relacionado com esse token
                    .withExpiresAt(dataExpiracao()) //Colocando valor maximo de 2 horas de funcionamento de um token, após isso um novo é gerado
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }

    }

    public String getSubject(String tokenJWT){
        try {
             var algoritmo = Algorithm.HMAC256(secret);
             return JWT.require(algoritmo)
                     .withIssuer("API Adega Santa Dose")
                     .build()
                     .verify(tokenJWT)
                     .getSubject();
        }catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); //Metodo para pegar a hora atual do pc e adicionar +2 horas com o fuso horario do brasil
        //Aí assim que chegar nessas duas 2 horas somadas, em comparação do que tava e o que vai estar no pc, ele vai excluir o token e gerar outro
    }
}
