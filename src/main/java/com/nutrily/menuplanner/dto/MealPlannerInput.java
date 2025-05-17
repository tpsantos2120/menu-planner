package com.nutrily.menuplanner.dto;

import lombok.Data;

import java.io.Serializable;

public record MealPlannerInput(
        Integer option,
        String mealDescription,
        MealTypeInput mealType,
        DietTypeInput dietType) implements Serializable {
}
