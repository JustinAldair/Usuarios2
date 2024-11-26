package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        entity.setUpdatedDate(Timestamp.from(Instant.now()));
        save(entity);
        return entity;
    }

    Optional<Usuario> findByUuid(UUID uuid);

    List<Usuario> findByActiveTrue();
}

