package com.trainibit.usuarios.controller;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.response.ApiErrorResponse;
import com.trainibit.usuarios.response.UsuarioResponse;
import com.trainibit.usuarios.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.action.internal.EntityAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


   /** @GetMapping
    public ResponseEntity<List<UsuarioResponse>>getUsuario(){

        return ResponseEntity.ok(usuarioService.findAll());
    }
    **/
   @GetMapping
   public ResponseEntity<List<UsuarioResponse>> getUsuario() {
       List<UsuarioResponse> usuarioResponses = usuarioService.findAll(); // Directamente desde el servicio
       return ResponseEntity.ok(usuarioResponses);
   }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        //Codigo de estatus HTTP 201
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try{
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();//204, eliminado correctamente
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //404 no enocntrado
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
//        try {
//            if (usuario.getName() == null || usuario.getName().isEmpty()) {
//                return new ResponseEntity<>(new ApiErrorResponse(
//                        "El nombre del usuario es obligatorio.",
//                        HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
//            }
//
//            if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
//                return new ResponseEntity<>(new ApiErrorResponse(
//                        "El correo del usuario es obligatorio.",
//                        HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
//            }
//
//            Usuario usuarioActualizado = usuarioService.update(id, usuario);
//            return ResponseEntity.ok(usuarioActualizado);
//
//        } catch (EntityNotFoundException e) {
//            // Si no se encuentra el usuario, lanzamos un error 404
//            ApiErrorResponse errorResponse = new ApiErrorResponse(
//                    "Recurso no encontrado: " + e.getMessage(),
//                    HttpStatus.NOT_FOUND.value());
//            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            if (usuario.getName() == null || usuario.getName().isEmpty()) {
                return new ResponseEntity<>(new ApiErrorResponse(
                        "El nombre del usuario es obligatorio.",
                        HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
            }

            if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
                return new ResponseEntity<>(new ApiErrorResponse(
                        "El correo del usuario es obligatorio.",
                        HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
            }

            Usuario usuarioActualizado = usuarioService.update(id, usuarioRequest);
            return ResponseEntity.ok(usuarioActualizado);

        } catch (EntityNotFoundException e) {
            // Si no se encuentra el usuario, lanzamos un error 404
            ApiErrorResponse errorResponse = new ApiErrorResponse(
                    "Recurso no encontrado: " + e.getMessage(),
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}