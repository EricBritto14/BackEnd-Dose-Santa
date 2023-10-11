package santaDose.voll.apiBack.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santaDose.voll.apiBack.pedidos.AgendaDePedidos;
import santaDose.voll.apiBack.pedidos.DadosCancelamentoProdutos;
import santaDose.voll.apiBack.pedidos.DadosDetalhamentoPedido;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;

@RestController
@RequestMapping("pedidos")
@SecurityRequirement(name = "bearer-key")
public class PedidosController {

    @Autowired //AutoWired para pedir pra ele injetar o produto neste objeto
    private AgendaDePedidos agenda;

    @PostMapping
    @Transactional
    public ResponseEntity pedir(@RequestBody @Valid DadosPedidoProdutos dados){
        var dto = agenda.pedir(dados);

        return ResponseEntity.ok(dto);
        //null, null para a quantidade e forma de pagamento
    }

    public ResponseEntity cancelarPedidos(@RequestBody @Valid DadosCancelamentoProdutos dados){
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();

    }
}
