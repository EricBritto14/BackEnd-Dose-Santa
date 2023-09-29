package santaDose.voll.apiBack.produtosVariedade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//CLASSE JPA = JAVA P API

@Table(name = "variedades") // Entidade e tabela dos bancos de dados
@Entity(name = "VariedadesProdutos")
@Getter //Criando todos os getters sem precisar codar
@NoArgsConstructor
//Criando construtor que não precisa de argumento  (Default que a JPA exige em todas a entidade) JPA = Java Persistence API
@AllArgsConstructor //Criando um cronstrutor que requer os argumentos
@EqualsAndHashCode(of = "id") //Gerar o EqualsAndHashCOde em cima do ID não de todos os outros
public class Variedades {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Gerar um valor unico em cima do ID
    private Long id;
    private String tipo;
    private String nome;
    private String valor;
    private String datavalidade;
    private String datacadastro;
    private String quantidade;
    private Boolean ativo;

    public Variedades(DadosCadastroVariedades dadosVariedade) {
        this.ativo = true;
        this.tipo = dadosVariedade.tipo();
        this.nome = dadosVariedade.nome();
        this.valor = dadosVariedade.valor();
        this.datavalidade = dadosVariedade.datavalidade();
        this.datacadastro = dadosVariedade.datacadastro();
        this.quantidade = dadosVariedade.quantidade();

    }

    public void atualizarInformacoesVariedades(DadosAtualizacaoVariedades dadosVariedade) {
        if(dadosVariedade.tipo() != null) {
            this.tipo = dadosVariedade.tipo();
        }
        if(dadosVariedade.nome() != null) {
            this.nome = dadosVariedade.nome();
        }
        if(dadosVariedade.valor() != null) {
            this.valor = dadosVariedade.valor();
        }
        if(dadosVariedade.datavalidade() != null) {
            this.datavalidade = dadosVariedade.datavalidade();
        }
        if(dadosVariedade.quantidade() != null) {
            this.quantidade = dadosVariedade.quantidade();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
