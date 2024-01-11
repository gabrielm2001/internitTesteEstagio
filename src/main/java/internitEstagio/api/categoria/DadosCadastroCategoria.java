package internitEstagio.api.categoria;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroCategoria(
        @NotNull
        String nome,
        @NotNull
        Long codigo
) {
}
