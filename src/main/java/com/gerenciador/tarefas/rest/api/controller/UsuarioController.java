package com.gerenciador.tarefas.rest.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.rest.api.model.Usuario;
import com.gerenciador.tarefas.rest.api.repository.UsuarioRepository;
import com.gerenciador.tarefas.rest.api.service.AuthenticationService;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = "application/json")
public class UsuarioController {
    
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> salvarUsuario(@RequestBody Usuario usuario) {
        long idUsuario = usuario.getIdUsuario();

        if(idUsuario == 0) {
            UserDetails usuarioBD = usuarioRepository.findByEmail(usuario.getEmail());

            if (Objects.nonNull(usuarioBD)) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Já existe um usuário cadastrado com este e-mail");
            }

            String senha = usuario.getSenha();

            BCryptPasswordEncoder encoder = authenticationService.getPasswordEncoder();

            usuario.setSenha(encoder.encode((senha)));
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository);
    }

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsuarioRepository usuarioRepository;

}
