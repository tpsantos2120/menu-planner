package com.nutrily.menuplanner.controller;

import lombok.Data;

@Data
public class MealPlannerFilter {
    private Integer option;
    private String mealDescription;
    private String mealType;
    private String dietType;
}
