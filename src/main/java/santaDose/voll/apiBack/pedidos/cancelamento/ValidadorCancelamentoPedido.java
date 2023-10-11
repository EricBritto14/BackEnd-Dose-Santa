package santaDose.voll.apiBack.pedidos.cancelamento;

import santaDose.voll.apiBack.pedidos.DadosCancelamentoProdutos;

public interface ValidadorCancelamentoPedido {

    void validar(DadosCancelamentoProdutos dados);
}
