package santaDose.voll.apiBack.produtosVariedade;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVariedades(
        @NotNull
        Long id,
        String tipo,
        String nome,
        String valor,
        String datavalidade,
        String quantidade){
}
