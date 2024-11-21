package com.trainibit.usuarios.mapper;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.response.UsuarioResponse;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioResponse mapEntityToDTO(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
            usuarioResponse.setId(usuario.getId());
            usuarioResponse.setName(usuario.getName());
            usuarioResponse.setLastName(usuario.getLastName());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setPassword(usuario.getPassword());
            usuarioResponse.setBirthDate(usuario.getBirthDate());
            usuarioResponse.setEdad(usuarioResponse.getEdad());
            return usuarioResponse;


    }

    public static List<UsuarioResponse> mapEntityListToDTOList(List<Usuario> usuarios) {
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioResponses.add(mapEntityToDTO(usuario));
        }
        return usuarioResponses;
    }
}
