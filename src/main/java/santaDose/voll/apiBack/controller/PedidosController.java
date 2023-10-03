package santaDose.voll.apiBack.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santaDose.voll.apiBack.pedidos.DadosDetalhamentoPedido;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;

@RestController
@RequestMapping("pedidos")
public class PedidosController {

    @PostMapping
    @Transactional
    public ResponseEntity pedir(@RequestBody @Valid DadosPedidoProdutos dados){
        System.out.println(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPedido(null, null, null, null, null, null));
    }
}
