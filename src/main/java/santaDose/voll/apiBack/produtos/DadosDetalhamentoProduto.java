package santaDose.voll.apiBack.produtos;

//Criando um novo record e novo construtor em seguida para a listagem e detalhamento do atualizar
public record DadosDetalhamentoProduto(Long id, String tipo, String nome, String valor, String datavalidade, String quantidade) {

    public DadosDetalhamentoProduto(Produto produto){
        this(produto.getId(), produto.getTipo(), produto.getNome(), produto.getValor(), produto.getDatavalidade(), produto.getQuantidade());
    }
}
