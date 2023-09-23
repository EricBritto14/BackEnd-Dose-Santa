package santaDose.voll.apiBack.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Criação de um novo record usando o DTO pois não tem como misturar com o de cadastro, além disso, criando um novo, podemos escolher quais campos podem ou não atualizar
public record DadosAtualizacaoProdutos(
        @NotNull
        Long id,
        String tipo,
        String nome,
        String valor,
        String datavalidade,
        String quantidade) {
}
