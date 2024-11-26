package com.trainibit.usuarios.mapper;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class  UsuarioMapper {

    public static UsuarioResponse mapEntityToDTO(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
//            usuarioResponse.setId(usuario.getId());
            usuarioResponse.setName(usuario.getName());
            usuarioResponse.setLastName(usuario.getLastName());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setPassword(usuario.getPassword());
            usuarioResponse.setBirthDate(usuario.getBirthDate());
            usuarioResponse.calcularEdad();
            usuarioResponse.setUuid(usuario.getUuid());
            return usuarioResponse;
    }

    public static List<UsuarioResponse> mapEntityListToDTOList(List<Usuario> usuarios) {
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioResponses.add(mapEntityToDTO(usuario));
        }
        return usuarioResponses;
    }

    public static Usuario mapRequestToEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setBirthDate(request.getBirthDate());
        usuario.setLastName(request.getLastName());
        usuario.setUuid(UUID.randomUUID());
        return usuario;
    }

}