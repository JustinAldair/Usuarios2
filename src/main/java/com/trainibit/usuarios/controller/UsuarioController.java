package com.trainibit.usuarios.controller;

import com.trainibit.usuarios.entity.Usuario;
import com.trainibit.usuarios.mapper.UsuarioMapper;
import com.trainibit.usuarios.request.UsuarioRequest;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


   @GetMapping
   public ResponseEntity<List<UsuarioResponse>> getUsuario() {
       return ResponseEntity.ok(usuarioService.findAll());
   }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable UUID uuid){
        return ResponseEntity.ok(usuarioService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> createUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioRequest));
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequest usuarioRequest) {
        try {
            if (usuarioRequest.getName() == null || usuarioRequest.getName().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new ApiErrorResponse("El nombre de usuario es obligatorio ", HttpStatus.BAD_REQUEST.value())
                );
            }

            if (usuarioRequest.getEmail() == null || usuarioRequest.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new ApiErrorResponse("El correo del usuario es obligatorio.", HttpStatus.BAD_REQUEST.value())
                );
            }

            UsuarioResponse usuarioActualizado = usuarioService.update(id, usuarioRequest);
            return ResponseEntity.ok(usuarioActualizado);

        } catch (EntityNotFoundException e) {
            // Si no se encuentra el usuario, lanzamos un error 404
            ApiErrorResponse errorResponse = new ApiErrorResponse(
                    "Recurso no encontrado: " + e.getMessage(),
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            ApiErrorResponse errorResponse = new ApiErrorResponse("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}