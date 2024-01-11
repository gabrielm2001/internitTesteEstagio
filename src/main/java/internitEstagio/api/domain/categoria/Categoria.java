package internitEstagio.api.domain.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorias")
@Entity(name = "Categoria")
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long codigo;

    private Boolean ativo;

    public Categoria(DadosCadastroCategoria dados){
        this.nome = dados.nome();
        this.codigo = dados.codigo();
        this.ativo = true;
    }
}
