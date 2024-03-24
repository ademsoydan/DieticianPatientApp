package com.tez.dieticianpatientapp.controller;

import com.tez.dieticianpatientapp.entities.Patient;
import com.tez.dieticianpatientapp.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("api/v1/patients")
    public ResponseEntity<?> findPatientByTCKN(@RequestHeader("X-TCKN") String tckn) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientByTckn(tckn));
    }

    @PostMapping("api/v1/patients")
    public ResponseEntity<String> bindDietician(@RequestParam Long patientId){
        patientService.bindDietician(patientId);
        return ResponseEntity.ok("İşlem Başarılı");
    }
}
