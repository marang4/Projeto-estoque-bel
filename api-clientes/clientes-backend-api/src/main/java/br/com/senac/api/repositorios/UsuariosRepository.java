package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
