package santaDose.voll.apiBack.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice //Para chamar o Sprint dentro da classe e do metodo, para ajudar nos retornos dos erros(advice)
public class TratadorDeErros {
    //Metodo para puxar o erro 500 se tornar o erro 404, entitynotfoundexception
    @ExceptionHandler(EntityNotFoundException.class) //@ExceptionHandler com EntityNotFoundException, para quando haver uma exception(erro) de codigo 404
    //Ele vai retornar esse metodo especifico e vai retornar erro 404
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build(); //Retornar o erro 404 em alguma exception

    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //@MethodArgumentNotValidException para quando haver uma exception(erro) de codigo 400
    //Ele vai retornar esse metodo especifico e vai retornar erro 400
    public ResponseEntity tratarErro400(MethodArgumentNotValidException erro400){
        var erro = erro400.getFieldErrors(); //Criando uma variavel para guardar qual das informações necessarias o usuario esqueceu de preencher
        return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList()); //Retornar o erro 400 em alguma exception
        //Esse ::new seria para chamar o construtor

    }

    @ExceptionHandler(ValidationException.class) //@MethodArgumentNotValidException para quando haver uma exception(erro) de codigo 400
    //Ele vai retornar esse metodo especifico e vai retornar erro 400
    public ResponseEntity tratarErroPedidoDeProduto(ValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage()); //Retornar o erro 400 em alguma exception
        //Esse ::new seria para chamar o construtor

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarErro400(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroCredenciais(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity tratarErroAuthentication(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso Negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro 500");
    }

    //Criando um DTO na mesma classe apenas para lidar com esses erro daqui mesmo
    //Criando também um construtor para retornar o campo de erro e a mensagem exata
    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());

        }

    }
}
