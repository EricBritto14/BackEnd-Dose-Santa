package santaDose.voll.apiBack.infra.exception;

import jakarta.persistence.EntityNotFoundException;
  import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    //Criando um DTO na mesma classe apenas para lidar com esses erro daqui mesmo
    //Criando também um construtor para retornar o campo de erro e a mensagem exata
    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());

        }

    }
}
