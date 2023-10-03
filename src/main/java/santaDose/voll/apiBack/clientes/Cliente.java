package santaDose.voll.apiBack.clientes;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//CLASSE JPA = JAVA P API

@Table(name = "clientes") // Entidade e tabela dos bancos de dados
@Entity(name = "Clientes")
@Getter //Criando todos os getters sem precisar codar
@NoArgsConstructor //Criando construtor que não precisa de argumento  (Default que a JPA exige em todas a entidade) JPA = Java Persistence API
@AllArgsConstructor //Criando um cronstrutor que requer os argumentos
@EqualsAndHashCode(of = "id") //Gerar o EqualsAndHashCOde em cima do ID não de todos os outros

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gerar um valor unico em cima do ID
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    //Para alterar alguma table ou algo no banco de dados como inserir um novo valor ... devemos aqui criar o private dessa nova variavel
    //Depois passar essa nova variavel no construtor
    //Depois passar ela no DadosCadastroProdutos
    //E por fim, criar uma nova migration no banco de dados por causa do flyway, então criar uma nova V3 e alterar o que quiser alterar

    //Criando o construtor que pega os exatos valores para retornar no ProdutosController e salvar no banco de dados
    public Cliente(DadosCadastroClientes dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = dados.endereco();

    }

    //Construtor para a atualização dos dados, deve ser null nois não vai retornar nada
    //Usando o IF para testar se o usuario tentou atualizar certo dado, se sim, atualiza, se não, n precisa atualizar não é obrigatório todos os campos
    public void atualizarInformacoesClientes(DadosAtualizacaoClientes dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }
        if(dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
