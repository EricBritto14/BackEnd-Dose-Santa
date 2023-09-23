package santaDose.voll.apiBack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Como é uma API Rest, para mostrar para a classe e para a main que está classe é um controller, no começo dela usar @RestController
//@RequestMapping para dizer qual mapeamento este controller vai responder, sempre que digitar a url /hello vai aparecer este controller
@RestController
@RequestMapping("/hello")
public class HelloController {

    //GetMapping para dizer ao metodo que ao chamar a URL do mapping, deve fazer o que está no metodo
    @GetMapping
    public String olaMundo(){
        return "Hello World";
    }

}
