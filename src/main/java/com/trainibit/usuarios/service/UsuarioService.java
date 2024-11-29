package com.trainibit.usuarios.service;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    List<UsuarioResponse> findAll();
    UsuarioResponse findById(UUID uuid);
    UsuarioResponse save(UsuarioRequest usuario);
    UsuarioResponse update(UUID uuid, UsuarioRequest updateUsuario);
    UsuarioResponse deleteById(UUID uuid);



}
