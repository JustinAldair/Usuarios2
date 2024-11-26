package com.trainibit.usuarios.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
public class UsuarioResponse {

    private String name;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private int edad;
    
    private UUID uuid;


    public void calcularEdad() {
        if (birthDate != null) {
            this.edad = Period.between(birthDate, LocalDate.now()).getYears();
        }
    }
}