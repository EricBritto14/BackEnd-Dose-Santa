package santaDose.voll.apiBack.produtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos") // Entidade e tabela dos bancos de dados
@Entity(name = "Produtos")
@Getter //Criando todos os getters sem precisar codar
@NoArgsConstructor //Criando construtor que não precisa de argumento  (Default que a JPA exige em todas a entidade) JPA = Java Persistence API
@AllArgsConstructor //Criando um cronstrutor que requer os argumentos
@EqualsAndHashCode(of = "id") //Gerar o EqualsAndHashCOde em cima do ID não de todos os outros
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Gerar um valor unico em cima do ID
    private Long id;
    private String tipo;
    private String nome;
    private String valor;
    private String tamanho;
    private String datavalidade;
    private String datacadastro;
    private String quantidade;

    //Criando o construtor que pega os exatos valores para retornar no ProdutosController e salvar no banco de dados
    public Produto(DadosCadastroProdutos dados) {
        this.tipo = dados.tipo();
        this.nome = dados.nome();
        this.valor = dados.valor();
        this.tamanho = dados.tamanho();
        this.datavalidade = dados.datavalidade();
        this.datacadastro = dados.datacadastro();
        this.quantidade = dados.quantidade();
    }
}
