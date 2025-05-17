package com.nutrily.menuplanner.dto;

import java.io.Serializable;

public record MealPlannerInput(
        Integer option,
        String mealDescription,
        MealTypeInput mealType,
        DietTypeInput dietType) implements Serializable {
}
