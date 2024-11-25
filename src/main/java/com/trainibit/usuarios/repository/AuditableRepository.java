package com.trainibit.usuarios.repository;

import com.trainibit.usuarios.entity.Usuario;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuditableRepository<T, ID> extends JpaRepository<T, ID> {

    void deleteByIdActivate (ID id);
    T  updateAudit (T t);


}
