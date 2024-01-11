package internitEstagio.api.domain.produto;

import internitEstagio.api.domain.categoria.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
@Entity(name = "Produto")
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String nome;

    private Long codigo;
    private String status;
    private Long quantidade;
    private String descricao;
    private String imagem;
    private Boolean ativo;

    public Produto(DadosCadastroProduto dados, Categoria categoria){
        this.nome = dados.nome();
        this.codigo = dados.codigo();
        this.status = dados.status();
        this.quantidade = dados.quantidade();
        this.descricao = dados.descricao();
        this.imagem = dados.imagem();
        this.nome = dados.nome();
        this.categoria = categoria;
        this.ativo = true;
    }

}
