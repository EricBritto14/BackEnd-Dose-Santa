package santaDose.voll.apiBack.pedidos.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santaDose.voll.apiBack.clientes.ClientesRepository;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;

@Component
public class ValidadorClienteAtivo implements ValidadorPedidoProduto  {

    @Autowired
    private ClientesRepository repository;

    public void validar(DadosPedidoProdutos dados){
       // var clienteEstaAtivo = repository.findAtivoById(dados.idPedinte());
        //if(!clienteEstaAtivo){
          //  throw new ValidationException("Pedido não pode ser feito por um cliente excluido");
     //   }
    }
}
