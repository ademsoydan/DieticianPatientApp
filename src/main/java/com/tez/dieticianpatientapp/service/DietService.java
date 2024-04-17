package com.tez.dieticianpatientapp.service;

import com.tez.dieticianpatientapp.dto.Request.DietCreate;
import com.tez.dieticianpatientapp.dto.Request.UpdateDietCalorieIntake;
import com.tez.dieticianpatientapp.entities.Diet;
import com.tez.dieticianpatientapp.entities.Dietician;
import com.tez.dieticianpatientapp.entities.Patient;
import com.tez.dieticianpatientapp.exception.DietNotFoundException;
import com.tez.dieticianpatientapp.exception.PatientNotMatchDieticianException;
import com.tez.dieticianpatientapp.repository.DietRepository;
import com.tez.dieticianpatientapp.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DietService {

    private DietRepository repository;
    DieticianService dieticianService;

    PatientService patientService;

    public void saveDiet(DietCreate dietCreate){
        UserDetailsImpl dieticianUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dietician dietician = dieticianService.getDieticianByTckn(dieticianUser.getUsername());
        Patient patient = patientService.getPatientById(dietCreate.patientId());
        if(patient.getDietician() == null || !Objects.equals(patient.getDietician().getId(), dietician.getId()))
            throw new PatientNotMatchDieticianException();
        List<Double> calValues = calculateValues(dietCreate.totalCal());
        Diet diet = new Diet(patient,dietician,dietCreate.totalCal(), calValues.get(0), calValues.get(1), calValues.get(2));
        repository.save(diet);
    }

    public Diet getDiet(long patientId){
        Patient patient = patientService.getPatientById(patientId);
        return findByPatientId(patient.getId());
    }

    public void updateCalorieIntake(UpdateDietCalorieIntake updateDiet){
        UserDetailsImpl patientUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.getPatientByTckn(patientUser.getUsername());
        Diet dbDiet = findByPatientId(patient.getId());
        dbDiet.setIntakeFat(dbDiet.getIntakeFat() + updateDiet.intakeFat());
        dbDiet.setIntakeProtein(dbDiet.getIntakeProtein() + updateDiet.intakeProtein());
        dbDiet.setIntakeCarbohydrate(dbDiet.getIntakeCarbohydrate() + updateDiet.intakeCarbohydrate());
        repository.save(dbDiet);
    }

    private List<Double> calculateValues(long totalCal){
        List<Double> calList = new ArrayList<>();
        double intakeCarbohydrate = (double) totalCal /3;
        double intakeFat = (double) totalCal /3;
        double intakeProtein = (double) totalCal /3;
        calList.add(intakeCarbohydrate);
        calList.add(intakeFat);
        calList.add(intakeProtein);
        return  calList;
    }

    public Diet findByPatientId(long patientId){
        return repository.findByPatientId(patientId).orElseThrow(() -> new DietNotFoundException("Bu hastaya ait bir diyet bilgisi bulunamadı"));
    }
}
