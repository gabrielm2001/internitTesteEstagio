package internitEstagio.api.domain.produto;

import jakarta.validation.constraints.NotBlank;

public record DadosDetalhamentoProduto(
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
        String imagem
) {
    public DadosDetalhamentoProduto(Produto produto){
        this(produto.getNome(), produto.getCodigo(), produto.getStatus(), produto.getQuantidade(), produto.getDescricao(), produto.getImagem());
    }
}
