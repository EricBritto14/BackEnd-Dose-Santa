package santaDose.voll.apiBack.clientes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//Dto
public record DadosEndereco (
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String rua,
        @NotBlank
        String numero) {


}
