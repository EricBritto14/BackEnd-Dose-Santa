package santaDose.voll.apiBack.clientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

public record DadosCadastroClientes(
        //Usando o beenValidation para na hora de cadastrar os produtos, não receber nenhum valor null ou blank pra tentar salvar no banco de dados
        //NotBlank só para campos String
        //Patter é para as variaveis de valores, assim determinando a quantidade minima de caracteres e a maxima
        @NotBlank
        String nome,
        @NotBlank
                @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotNull
        String telefone,

        @NotNull
        @Valid
        Endereco endereco
) {

}
