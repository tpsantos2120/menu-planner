package com.nutrily.menuplanner.controller;

import com.nutrily.menuplanner.entity.MealPlanner;

import java.util.List;

public record MealPlannerPage(List<MealPlanner> content, long totalElements, int totalPages, int size, int number) {
}
