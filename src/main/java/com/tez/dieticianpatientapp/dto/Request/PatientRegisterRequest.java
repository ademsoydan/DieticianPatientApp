package com.tez.dieticianpatientapp.dto.Request;

public record PatientRegisterRequest (
        String firstName,
        String lastName,
        String tckn,
        String password
){
}
