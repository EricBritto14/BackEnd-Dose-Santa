package santaDose.voll.apiBack.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

public record DadosCadastroVariedades(
    @NotBlank
    String tipo,
    @NotBlank
    String nome,
    @NotBlank
    String valor,
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
