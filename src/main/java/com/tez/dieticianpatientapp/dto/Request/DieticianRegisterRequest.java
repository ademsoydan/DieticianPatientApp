package com.tez.dieticianpatientapp.dto.Request;

import jakarta.persistence.Column;

public record DieticianRegisterRequest(
        String firstName,
        String lastName,
        String tckn,
        String password
) {
}
