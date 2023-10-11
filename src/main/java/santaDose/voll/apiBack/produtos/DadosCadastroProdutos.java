package santaDose.voll.apiBack.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import santaDose.voll.apiBack.teste.TesteRecord;

import java.util.Date;

//O Record serve para não precisar criar os get, construtor e setters. Padrão DTO - Data transfer Objects
public record DadosCadastroProdutos(
        //Usando o beenValidation para na hora de cadastrar os produtos, não receber nenhum valor null ou blank pra tentar salvar no banco de dados
        //NotBlank só para campos String
        //Patter é para as variaveis de valores, assim determinando a quantidade minima de caracteres e a maxima
        @NotNull
        Long id,
        @NotBlank
        String tipo,
        @NotBlank
        String nome,
        @NotBlank
        String valor,
        @NotBlank
                @Pattern(regexp = "\\d{1,4}")
        String tamanho,

        @NotNull
        String mlL,
        @NotBlank
                @DateTimeFormat
        String datavalidade,
        @NotBlank
                @DateTimeFormat
        String datacadastro,
        @NotNull
                @Pattern(regexp = "\\d{1,4}")
        String quantidade) {

}


