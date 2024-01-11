package internitEstagio.api.controller;

import internitEstagio.api.domain.categoria.CategoriaRepository;
import internitEstagio.api.domain.produto.DadosCadastroProduto;
import internitEstagio.api.domain.produto.DadosDetalhamentoProduto;
import internitEstagio.api.domain.produto.Produto;
import internitEstagio.api.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity cadastrar(@PathVariable Long id, @RequestBody DadosCadastroProduto dados, UriComponentsBuilder uriBuilder){
        var categoria = categoriaRepository.getReferenceById(id);
        var produto = new Produto(dados, categoria);
        produtoRepository.save(produto);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Page<DadosDetalhamentoProduto>> listarTodosProdutosDecategoria(@PathVariable Long id, Pageable paginacao){
        var categoria = categoriaRepository.getReferenceById(id);
        var page = produtoRepository.findAllByAtivoTrueAndCategoriaId(paginacao, categoria.getId()).map(DadosDetalhamentoProduto::new);

        return ResponseEntity.ok().body(page);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoProduto>> listarTodosProdutos(Pageable paginacao){
        var page = produtoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhamentoProduto::new);

        return ResponseEntity.ok().body(page);
    }

}
