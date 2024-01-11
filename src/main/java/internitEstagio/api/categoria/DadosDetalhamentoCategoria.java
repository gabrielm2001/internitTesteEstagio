package internitEstagio.api.categoria;

import internitEstagio.api.categoria.Categoria;
import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoCategoria(        @NotNull
                                                 String nome,
                                                 @NotNull
                                                 Long codigo) {
    public DadosDetalhamentoCategoria(Categoria categoria){
        this(categoria.getNome(), categoria.getCodigo());
    }
}
