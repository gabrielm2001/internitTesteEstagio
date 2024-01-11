package internitEstagio.api.produto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record DadosCadastroProduto(
        @NotBlank
        String nome,
        @NotBlank
        Long codigo,
        @NotBlank
        String status,
        @NotBlank
        Long quantidade,
        @NotBlank
        String descricao,
        @NotBlank
        @URL
        String imagem
) {
}
