package com.trainibit.usuarios.service.impl;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.repository.UsuarioRepository;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceimpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponse> findAll() {
        List<Usuario> usuariosActivos = usuarioRepository.findByActiveTrue();
        //return UsuarioMapper.mapEntityListToDTOList(usuarioRepository.findAll());
        return UsuarioMapper.mapEntityListToDTOList(usuariosActivos);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //    @Override
//    public Usuario update(Long id, Usuario usuario) throws IllegalAccessException {
//    if(usuarioRepository.existsById(id)) {
//        usuario.setId(id);
//        return usuarioRepository.save(usuario);
//    }else{
//        throw
//        new IllegalAccessException("Usuario con id" + id + " no encontrado");
//    }
//}
    @Override
    public Usuario update(Long id, Usuario updateUsuario) throws IllegalAccessException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() ->
                new IllegalAccessException("Usuario con id " + id + " no encontrado"));

        // Actualizar los campos del usuario con los del updateUsuario
        usuario.setName(updateUsuario.getName());
        usuario.setLastName(updateUsuario.getLastName());
        usuario.setEmail(updateUsuario.getEmail());
        usuario.setPassword(updateUsuario.getPassword());
        usuario.setBirthDate(updateUsuario.getBirthDate());

        return usuarioRepository.save(usuario);
    }


    @Override
    public void deleteById(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Usuario con id " + id + " no encontrado");
        }
    }
}
