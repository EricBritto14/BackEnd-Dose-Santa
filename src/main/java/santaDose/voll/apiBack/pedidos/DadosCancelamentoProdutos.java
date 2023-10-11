package santaDose.voll.apiBack.pedidos;

import jakarta.validation.constraints.NotNull;

//JPA dados para requisitar do cancelamento
public record DadosCancelamentoProdutos(
    @NotNull
    Long idProduto,

    @NotNull
    Long idPedinte,
    @NotNull
    Long idVariedades,

    @NotNull
    MotivoCancelamento motivo){
}
