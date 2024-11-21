package br.com.senac.api.controllers;

import br.com.senac.api.entidades.Endereco;
import br.com.senac.api.entidades.Produto;
import br.com.senac.api.repositorios.EnderecoRepository;
import br.com.senac.api.repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/criar")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto){
        Produto retorno =  produtoRepository.save(produto);
        if (retorno != null) {
            return ResponseEntity.ok().body(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarProduto() {
        List<Produto> produtoRetorno = produtoRepository.findAll();

        return ResponseEntity.ok().body(produtoRetorno);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){

        produtoRepository.deleteById(id);

        return ResponseEntity.ok().body(null);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizarEndereco(@RequestBody Produto produto, @PathVariable Long id){
        Optional<Produto> retorno =  produtoRepository.findById(id).map(record -> {
            record.setNome(produto.getNome());
            record.setDescricao(produto.getDescricao());
            record.setUrlImagem(produto.getUrlImagem());
            record.setAltura(produto.getAltura());
            record.setLargura(produto.getLargura());
            record.setComprimento(produto.getComprimento());
            record.setPesoLiquido(produto.getPesoLiquido());
            record.setPesoBruto(produto.getPesoBruto());

            return produtoRepository.save(record);
        });
        if (retorno.isPresent()){
            return ResponseEntity.ok().body(retorno.get());
        }
        return ResponseEntity.badRequest().body(null);
    }


}
