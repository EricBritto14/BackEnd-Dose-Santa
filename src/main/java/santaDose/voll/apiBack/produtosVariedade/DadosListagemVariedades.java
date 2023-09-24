package santaDose.voll.apiBack.produtosVariedade;

public record DadosListagemVariedades(Long id, String tipo, String nome, String valor, String datavalidade, String datacadastro, String quantidade) {

    public DadosListagemVariedades(Variedades variedades){
        this(variedades.getId(), variedades.getTipo(), variedades.getNome(), variedades.getValor(), variedades.getDatavalidade(), variedades.getDatacadastro(), variedades.getQuantidade());
    }
}
