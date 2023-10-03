package santaDose.voll.apiBack.clientes;

import jakarta.validation.Valid;

//Jpa de atualização
public record DadosAtualizacaoClientes(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        DadosEndereco endereco

) {
}
