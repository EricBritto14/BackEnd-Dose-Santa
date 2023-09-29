package santaDose.voll.apiBack.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santaDose.voll.apiBack.infra.security.TokenService;
import santaDose.voll.apiBack.usuario.DadosAutenticacao;
import santaDose.voll.apiBack.usuario.Usuario;

@RestController //@ Padrão para um controller
@RequestMapping("/login") // Indicar qual url chamará esse controller
public class AutenticacaoController {

    @Autowired //Para indicar pro spring q ele mesmo injetar esse parametro, não nós
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping //Post pois esta recebendo dados
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //UserNamePassword e etc, é a classe do proprio String usada como padrão, onde nela a gente só precisa passar nosso DTO no caso o login e a senha nosso, passando no DTO deles (classe deles)
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
    }
}
