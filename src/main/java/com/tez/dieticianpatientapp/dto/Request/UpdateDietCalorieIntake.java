package com.tez.dieticianpatientapp.dto.Request;

public record UpdateDietCalorieIntake(
        double intakeCarbohydrate,
        double intakeFat,
        double intakeProtein) {
}
