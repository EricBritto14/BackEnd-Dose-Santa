package santaDose.voll.apiBack.clientes;

import santaDose.voll.apiBack.produtos.Produto;
//DTO
public record DadosListagemClientes(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DadosListagemClientes(Cliente clientes){
        this(clientes.getId(), clientes.getNome(), clientes.getEmail(), clientes.getTelefone(), clientes.getCpf(), clientes.getEndereco());


    }
}
