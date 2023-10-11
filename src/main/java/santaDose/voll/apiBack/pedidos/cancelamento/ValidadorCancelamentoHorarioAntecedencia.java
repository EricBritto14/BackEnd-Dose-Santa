package santaDose.voll.apiBack.pedidos.cancelamento;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import santaDose.voll.apiBack.pedidos.DadosCancelamentoProdutos;
import santaDose.voll.apiBack.pedidos.PedidosRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoHorarioAntecedencia implements ValidadorCancelamentoPedido{
    @Autowired
    private PedidosRepository pedidosRepository;

    @Override
    public void validar(DadosCancelamentoProdutos dados) {
        var pedido = pedidosRepository.getReferenceById(dados.idProduto());
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, pedido.getData()).toMinutes();

        if (diferencaEmMinutos < 35) {
            throw new ValidationException("Consulta somente pode ser cancelada com antecedência mínima de 30 mins!");
        }
    }
}

