package com.nutrily.menuplanner.service;

import com.nutrily.menuplanner.entity.DietType;
import com.nutrily.menuplanner.entity.MealPlanner;
import com.nutrily.menuplanner.entity.MealType;
import com.nutrily.menuplanner.dto.DietTypeInput;
import com.nutrily.menuplanner.dto.MealPlannerInput;
import com.nutrily.menuplanner.dto.MealTypeInput;
import com.nutrily.menuplanner.repository.DietTypeRepository;
import com.nutrily.menuplanner.repository.MealPlannerRepository;
import com.nutrily.menuplanner.repository.MealTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MealPlannerService {
    
    private final MealPlannerRepository mealPlannerRepository;
    private final MealTypeRepository mealTypeRepository;
    private final DietTypeRepository dietTypeRepository;
    
    public MealPlannerService(
            MealPlannerRepository mealPlannerRepository,
            MealTypeRepository mealTypeRepository,
            DietTypeRepository dietTypeRepository) {
        this.mealPlannerRepository = mealPlannerRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.dietTypeRepository = dietTypeRepository;
    }
    
    @Transactional
    public MealPlanner createMealPlanner(MealPlannerInput input) {
        MealPlanner mealPlanner = new MealPlanner();
        mealPlanner.setOption(input.option());
        mealPlanner.setMealDescription(input.mealDescription());

        if (input.mealType() != null) {
            mealTypeRepository.findByType(input.mealType().type())
                    .ifPresent(mealPlanner::setMealType);
        }

        if (input.dietType() != null) {
            dietTypeRepository.findByType(input.dietType().type())
                    .ifPresent(mealPlanner::setDietType);
        }

        return mealPlannerRepository.save(mealPlanner);
    }
    
    @Transactional
    public MealPlanner updateMealPlanner(UUID id, MealPlannerInput input) {
        Optional<MealPlanner> existingMealPlanner = mealPlannerRepository.findById(id);
        
        if (existingMealPlanner.isPresent()) {
            MealPlanner mealPlanner = existingMealPlanner.get();
            
            if (input.option() != null) {
                mealPlanner.setOption(input.option());
            }
            
            if (input.mealDescription() != null) {
                mealPlanner.setMealDescription(input.mealDescription());
            }
            
            if (input.mealType() != null) {
                mealTypeRepository.findByType(input.mealType().type())
                        .ifPresent(mealPlanner::setMealType);
            }
            
            if (input.dietType() != null) {
                dietTypeRepository.findByType(input.dietType().type())
                        .ifPresent(mealPlanner::setDietType);
            }
            
            return mealPlannerRepository.save(mealPlanner);
        }
        
        return null;
    }
    
    @Transactional
    public boolean deleteMealPlanner(UUID id) {
        if (mealPlannerRepository.existsById(id)) {
            mealPlannerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public MealType createMealType(MealTypeInput input) {
        // Check if a meal type with the same type already exists
        Optional<MealType> existingMealType = mealTypeRepository.findByType(input.type());
        if (existingMealType.isPresent()) {
            return existingMealType.get();
        }
        
        // Create a new meal type
        MealType mealType = new MealType();
        mealType.setType(input.type());
        return mealTypeRepository.save(mealType);
    }
    
    @Transactional
    public DietType createDietType(DietTypeInput input) {
        // Check if a diet type with the same type already exists
        Optional<DietType> existingDietType = dietTypeRepository.findByType(input.type());
        if (existingDietType.isPresent()) {
            return existingDietType.get();
        }
        
        // Create a new diet type
        DietType dietType = new DietType();
        dietType.setType(input.type());
        return dietTypeRepository.save(dietType);
    }
}
