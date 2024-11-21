package br.com.senac.api.controllers;


import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.entidades.Usuario;
import br.com.senac.api.repositorios.ProdutoRepository;
import br.com.senac.api.repositorios.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
@CrossOrigin
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        Usuario retorno = usuariosRepository.findByEmail(usuario.getEmail());
        if (retorno != null) {
            if(retorno.getSenha().equals(usuario.getSenha())){
                return ResponseEntity.ok().body(retorno);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }


}
