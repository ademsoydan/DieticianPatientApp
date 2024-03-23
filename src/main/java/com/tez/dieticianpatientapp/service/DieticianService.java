package com.tez.dieticianpatientapp.service;

import com.tez.dieticianpatientapp.dto.DieticianDto;
import com.tez.dieticianpatientapp.entities.Dietician;
import com.tez.dieticianpatientapp.entities.User;
import com.tez.dieticianpatientapp.exception.NotUniqueTcknException;
import com.tez.dieticianpatientapp.exception.UserNotFoundException;
import com.tez.dieticianpatientapp.repository.DieticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DieticianService {

    @Autowired
    DieticianRepository dieticianRepository;

    @Autowired
    UserService userService;

    public ResponseEntity<?> saveDietician(Dietician dietician){
        try {
            dieticianRepository.save(dietician);
        }
        catch (DataIntegrityViolationException ex){
            throw new NotUniqueTcknException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new DieticianDto(dietician));
    }

    public ResponseEntity<DieticianDto> getDieticianByTckn(String tckn){
        User user = userService.getUserByTckn(tckn).orElseThrow(UserNotFoundException::new);
        Dietician dietician = dieticianRepository.findByUserTckn(user.getTckn()).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(new DieticianDto(dietician));
    }

    public ResponseEntity<DieticianDto> getDieticianById(long id){
        Dietician dietician = dieticianRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(new DieticianDto(dietician));
    }
}
