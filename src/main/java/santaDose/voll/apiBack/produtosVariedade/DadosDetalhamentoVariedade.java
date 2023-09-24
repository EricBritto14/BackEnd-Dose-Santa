package santaDose.voll.apiBack.produtosVariedade;

import santaDose.voll.apiBack.produtosVariedade.Variedades;

public record DadosDetalhamentoVariedade(Long id, String tipo, String nome, String valor, String datavalidade, String datacadastro, String quantidade) {

    public DadosDetalhamentoVariedade(Variedades variedades){
        this(variedades.getId(), variedades.getTipo(), variedades.getNome(), variedades.getValor(), variedades.getDatavalidade(), variedades.getDatacadastro(), variedades.getQuantidade());

    }
}
