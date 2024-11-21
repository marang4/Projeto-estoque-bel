package br.com.senac.api.controllers;


import br.com.senac.api.entidades.Endereco;
import br.com.senac.api.repositorios.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping("/criar")
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco){
        Endereco retorno =  enderecoRepository.save(endereco);
        if (retorno != null) {
            return ResponseEntity.ok().body(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> listarEndereco() {
        List<Endereco> enderecoRetorno = enderecoRepository.findAll();

        return ResponseEntity.ok().body(enderecoRetorno);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id){

        enderecoRepository.deleteById(id);

        return ResponseEntity.ok().body(null);

    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@RequestBody Endereco endereco, @PathVariable Long id){
        Optional<Endereco> retorno =  enderecoRepository.findById(id).map(record -> {
            record.setRua(endereco.getRua());
            record.setBairro(endereco.getBairro());
            record.setCidade(endereco.getCidade());
            record.setUf(endereco.getUf());
            record.setResponsavel(endereco.getResponsavel());

            return enderecoRepository.save(record);
        });
        if (retorno.isPresent()){
            return ResponseEntity.ok().body(retorno.get());
        }
        return ResponseEntity.badRequest().body(null);
    }
}
