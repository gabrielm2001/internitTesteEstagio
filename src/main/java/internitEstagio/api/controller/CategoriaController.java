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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCategoria>> listar(@PageableDefault(size = 10) Pageable paginacao){
        var page = categoriaRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoCategoria::new);

        return ResponseEntity.ok().body(page);
    }

}
