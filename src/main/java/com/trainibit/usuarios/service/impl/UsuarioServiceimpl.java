package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.request.UsuarioRequest;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceimpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> findAll() {

        return UsuarioMapper.mapEntityListToDTOList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponse findById(UUID uuid) {
        return UsuarioMapper.mapEntityToDTO(usuarioRepository.findByUuidAndActiveTrue(uuid).get());
    }

    @Override
    public UsuarioResponse save(UsuarioRequest usuario) {
        return UsuarioMapper.mapEntityToDTO(usuarioRepository.save(UsuarioMapper.mapRequestToEntity(usuario)));
    }

    @Override
    public UsuarioResponse update(UUID uuid, UsuarioRequest updateUsuario){
        Usuario usuario = usuarioRepository.findByUuidAndActiveTrue(uuid).orElseThrow(() ->
                new EntityNotFoundException("Usuario con id " + uuid + " no encontrado"));

        // Actualizar los campos del usuario con los del updateUsuario
        usuario.setName(updateUsuario.getName());
        usuario.setLastName(updateUsuario.getLastName());
        usuario.setEmail(updateUsuario.getEmail());
        usuario.setPassword(updateUsuario.getPassword());
        usuario.setBirthDate(updateUsuario.getBirthDate());

        Usuario usuarioActual = usuarioRepository.save(usuario);

        return UsuarioMapper.mapEntityToDTO(usuarioActual);
    }


//    @Override
//    public void deleteById(Long id) {
//        if (!usuarioRepository.existsById(id)) {
//            throw new EntityNotFoundException("Usuario con id " + id + " no encontrado");
//        }
//        usuarioRepository.deleteByIdAndActiveTrue(id);


    @Override
    public UsuarioResponse deleteById(UUID uuid) {
        Usuario usuario = usuarioRepository.findByUuidAndActiveTrue(uuid).
                orElseThrow(()-> new EntityNotFoundException("Usuario con id " + uuid + " no encontrado"));

        //cambiar el estado de active segun boorado logico
        usuario.setActive(false);

        //Guardar   los cmabios en bd
        Usuario usuarioActual = usuarioRepository.save(usuario);
        return UsuarioMapper.mapEntityToDTO(usuarioActual);
    }
}