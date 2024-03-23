package com.tez.dieticianpatientapp.controller;

import com.tez.dieticianpatientapp.dto.Requset.DiteticianCreate;
import com.tez.dieticianpatientapp.service.DieticianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DieticianController {

    @Autowired
    DieticianService dieticianService;

    @PostMapping("api/v1/dieticians")
    public ResponseEntity<?> saveDietician(@Valid @RequestBody DiteticianCreate diteticianCreate){
        return dieticianService.saveDietician(diteticianCreate.toDietician());
    }

}
