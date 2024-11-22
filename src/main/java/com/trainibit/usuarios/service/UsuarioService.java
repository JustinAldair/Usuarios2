package com.trainibit.usuarios.service;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<UsuarioResponse> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario) throws IllegalAccessException;
    void deleteById(Long id);
}
