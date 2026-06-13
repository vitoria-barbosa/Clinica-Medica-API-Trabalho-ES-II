package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
