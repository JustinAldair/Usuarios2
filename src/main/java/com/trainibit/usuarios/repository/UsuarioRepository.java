package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends AuditableRepository<Usuario, Long> {


//    @Override
//    default void deleteById(Long id) {
//        Usuario entity = findById(id).orElseThrow(()-> new RuntimeException("ID no encontrado"));
//        entity.setActive(false);
//        save(entity);
//    }

default void deleteByIdActive(UUID uuid) {
    Usuario usuario = findByUuidAndActiveTrue(uuid).get();
    usuario.setActive(false);
    save(usuario);

}

Optional<Usuario> findByUuidAndActiveTrue(UUID uuid);


    @Override
    default Usuario updateAudit(Usuario entity) {
        entity.setUpdatedDate(Timestamp.from(Instant.now()));

        return save(entity);
    }

    List<Usuario> findByActiveTrue();
}

