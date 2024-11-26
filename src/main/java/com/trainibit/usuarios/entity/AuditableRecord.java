package com.trainibit.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class AuditableRecord {
    @Column(name = "created_at",  updatable = false, insertable = false)
    private Timestamp createdDate;

    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedDate;

    @ColumnDefault("true")
    @Column(name = "active")
    private boolean active = false;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
