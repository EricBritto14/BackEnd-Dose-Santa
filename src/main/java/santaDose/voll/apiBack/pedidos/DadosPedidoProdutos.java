package santaDose.voll.apiBack.pedidos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosPedidoProdutos(
    Long idProduto,

    @NotNull
    Long idPedinte,

    @NotNull
    String quantidade,

    @NotNull
    String formaPagamento,

    @NotNull
    @FutureOrPresent
    LocalDateTime data) {
}
