package com.tez.dieticianpatientapp.controller;

import com.tez.dieticianpatientapp.dto.DieticianDto;
import com.tez.dieticianpatientapp.dto.Request.DiteticianCreate;
import com.tez.dieticianpatientapp.service.DieticianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DieticianController {

    @Autowired
    DieticianService dieticianService;

    @GetMapping("api/v1/dieticians/{id}")
    public ResponseEntity<DieticianDto> getDietician(@PathVariable long id){
        return dieticianService.getDieticianById(id);
    }

}
