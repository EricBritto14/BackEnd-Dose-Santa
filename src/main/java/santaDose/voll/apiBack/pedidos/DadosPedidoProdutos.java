package santaDose.voll.apiBack.pedidos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record DadosPedidoProdutos(
        @NotNull
    Long idProduto,

    @NotNull
    Long idPedinte,

    @NotNull
    Long idVariedades,
//    @NotNull
//    Long quantidade,

        //Criar aqui os String ou Long para forma de pagamento e quantidade
      @NotNull
    @FutureOrPresent
    LocalDateTime data) {
}
