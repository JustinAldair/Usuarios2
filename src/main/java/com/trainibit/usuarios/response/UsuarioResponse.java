package com.trainibit.usuarios.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class UsuarioResponse {


    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private int edad;

    // Método para calcular la edad
    public void calcularEdad() {
        if (birthDate != null) {
            this.edad = Period.between(birthDate, LocalDate.now()).getYears();
        }
    }
}