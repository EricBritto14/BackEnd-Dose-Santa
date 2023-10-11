package santaDose.voll.apiBack.pedidos;

import java.time.LocalDateTime;

public record DadosDetalhamentoPedido(Long id, Long idProduto, Long idPedinte, LocalDateTime data) {
    public DadosDetalhamentoPedido(Pedido pedido) {
        this(pedido.getId(), pedido.getProduto().getId(), pedido.getCliente().getId(), pedido.getData());
    }
    //String quantidade, String formaPagamento,
}
