package com.trainibit.usuarios.service;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
}
