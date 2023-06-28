package com.gerenciador.tarefas.rest.api.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.tarefas.rest.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
    Page<Usuario> findByNomeLike(Pageable pageable, String nome);

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);

}
