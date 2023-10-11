package santaDose.voll.apiBack.pedidos.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;

@Component
public class ValidadorHorarioAdega implements ValidadorPedidoProduto  {

    //Criando metodo de validação da data e da hora do pedido
    public void validar(DadosPedidoProdutos dados){

        var dataPedido = dados.data();
        var antesDaAberturaDaAdega = dataPedido.getHour() < 10;
        var depoisDoEncerramentoDaAdega = dataPedido.getHour() > 22;
        if (antesDaAberturaDaAdega || depoisDoEncerramentoDaAdega){
            throw new ValidationException("Pedido fora do horário de funcionamento da Adega!");
        }
    }

}
