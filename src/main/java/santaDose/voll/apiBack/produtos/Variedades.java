package santaDose.voll.apiBack.produtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Variedades(DadosCadastroVariedades dadosVariedade) {
        this.tipo = dadosVariedade.tipo();
        this.nome = dadosVariedade.nome();
        this.valor = dadosVariedade.valor();
        this.datavalidade = dadosVariedade.datavalidade();
        this.datacadastro = dadosVariedade.datacadastro();
        this.quantidade = dadosVariedade.quantidade();

    }
}
