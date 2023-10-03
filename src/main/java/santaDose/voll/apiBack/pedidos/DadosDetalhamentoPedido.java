package santaDose.voll.apiBack.pedidos;

import java.time.LocalDateTime;

public record DadosDetalhamentoPedido(Long id, Long idProduto, Long idPedinte, String quantidade, String formaPagamento, LocalDateTime data) {
}
