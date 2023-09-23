package santaDose.voll.apiBack.produtos;

public record DadosListagemProdutos(String tipo, String nome, String valor, String tamanho, String mlL, String datavalidade, String datacadastro, String quantidade) {

    public DadosListagemProdutos(Produto produtos){
        this(produtos.getTipo(), produtos.getNome(), produtos.getValor(), produtos.getTamanho(), produtos.getMlL(), produtos.getDatavalidade(), produtos.getDatacadastro(), produtos.getQuantidade());


    }
}
