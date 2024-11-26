package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface UsuarioRepository extends AuditableRepository<Usuario, Long> {


    @Override
    default void deleteById(Long id) {
        Usuario entity = findById(id).orElseThrow(()-> new RuntimeException("ID no found"));
        entity.setActive(false);
        save(entity);
    }

    @Override
    default Usuario updateAudit(Usuario entity) {
        entity.setUpdatedDate(LocalDate.now());
        save(entity);
        return entity;
    }

    List<Usuario> findByActiveTrue();
}

