package internitEstagio.api.controller;

import internitEstagio.api.domain.categoria.Categoria;
import internitEstagio.api.domain.categoria.CategoriaRepository;
import internitEstagio.api.domain.categoria.DadosCadastroCategoria;
import internitEstagio.api.domain.categoria.DadosDetalhamentoCategoria;

import internitEstagio.api.domain.produto.DadosDetalhamentoProduto;
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosDetalhamentoCategoria dados){
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.atualizar(dados);

        return ResponseEntity.ok().body(new DadosDetalhamentoCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.deletar();
        return ResponseEntity.ok().body(new DadosDetalhamentoCategoria(categoria));
    }

}
