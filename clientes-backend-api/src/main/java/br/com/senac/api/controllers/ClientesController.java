package br.com.senac.api.controllers;

import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("clientes")
@CrossOrigin
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;


    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listarClientes() { //para retornar uma lista de clientes, se nao colocar list retorna somente a classe cliente
        List<Clientes> clientesRetorno = clientesRepository.findAll();

        return ResponseEntity.ok().body(clientesRetorno);
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criarCliente(@RequestBody Clientes cliente){
      Clientes retorno =   clientesRepository.save(cliente);
      if (retorno != null) {
          return ResponseEntity.ok().body(retorno);
      }
      return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizarCliente(@RequestBody Clientes cliente, @PathVariable Long id){
        Optional<Clientes> retorno =  clientesRepository.findById(id).map(record -> {
            record.setNome(cliente.getNome());

            return clientesRepository.save(record);
        });
        if (retorno.isPresent()){
            return ResponseEntity.ok().body(retorno.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){   //void entre <> por que nao tem retorno

        clientesRepository.deleteById(id);

        return ResponseEntity.ok().body(null);

    }



}
