package santaDose.voll.apiBack.pedidos.validacoes;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santaDose.voll.apiBack.pedidos.DadosPedidoProdutos;
import santaDose.voll.apiBack.pedidos.PedidosRepository;
import santaDose.voll.apiBack.pedidos.cancelamento.ValidadorCancelamentoPedido;

import java.time.Duration;
import java.time.LocalDateTime;

@Component //Anotação que não é para uma classe de configuração, nem um controller nem um aservice
//Anotação que indica que é um componente genérico, para carregar no começo do projeto
public class ValidadorHorarioAntecedencia implements ValidadorPedidoProduto {
    public void validar(DadosPedidoProdutos dados){
        var dataPedido = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataPedido).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidationException("Pedido deve ser feito com antecedência mínima de 30 minutos");
        }
    }
}
