package internitEstagio.api.controller;

import internitEstagio.api.categoria.Categoria;
import internitEstagio.api.categoria.CategoriaRepository;
import internitEstagio.api.categoria.DadosCadastroCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder){
        var categoriaJpa = new Categoria(dados);
        var categoria = categoriaRepository.save(categoriaJpa);
        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

}
