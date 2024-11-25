package com.trainibit.usuarios.entity;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

 
public class AuditableRecord {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedDate;

    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
