package com.nutrily.menuplanner.controller;

import com.nutrily.menuplanner.entity.DietType;
import com.nutrily.menuplanner.entity.MealPlanner;
import com.nutrily.menuplanner.repository.MealPlannerRepository;
import com.nutrily.menuplanner.entity.MealType;
import com.nutrily.menuplanner.dto.DietTypeInput;
import com.nutrily.menuplanner.dto.MealPlannerInput;
import com.nutrily.menuplanner.service.MealPlannerService;
import com.nutrily.menuplanner.dto.MealTypeInput;
import com.nutrily.menuplanner.specification.MealPlannerSpecification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MealPlannerController {
    
    private final MealPlannerRepository mealPlannerRepository;
    private final MealPlannerService mealPlannerService;
    
    public MealPlannerController(MealPlannerRepository mealPlannerRepository, MealPlannerService mealPlannerService) {
        this.mealPlannerRepository = mealPlannerRepository;
        this.mealPlannerService = mealPlannerService;
    }
    
    @QueryMapping
    public List<MealPlanner> mealPlanners() {
        return mealPlannerRepository.findAll();
    }
    
    @QueryMapping
    public MealPlannerPage mealNPlanners(
            @Argument Integer page, 
            @Argument Integer size, 
            @Argument MealPlannerFilter filters) {
        
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);

        Specification<MealPlanner> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters != null) {
                if (filters.getOption() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("option"), filters.getOption()));
                }
                if (filters.getMealDescription() != null) {
                    predicates.add(criteriaBuilder.like(root.get("mealDescription"), 
                            "%" + filters.getMealDescription() + "%"));
                }
                if (filters.getMealType() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("mealType").get("type"), 
                            filters.getMealType()));
                }
                if (filters.getDietType() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("dietType").get("type"), 
                            filters.getDietType()));
                }
            }

            return predicates.isEmpty() 
                    ? criteriaBuilder.conjunction() 
                    : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<MealPlanner> mealPlannerPage = mealPlannerRepository.findAll(spec, pageable);

        return new MealPlannerPage(
                mealPlannerPage.getContent(),
                mealPlannerPage.getTotalElements(),
                mealPlannerPage.getTotalPages(),
                mealPlannerPage.getSize(),
                mealPlannerPage.getNumber()
        );
    }
    
    @QueryMapping
    public Optional<MealPlanner> mealPlanner(@Argument UUID id) {
        return mealPlannerRepository.findById(id);
    }
    
    @QueryMapping
    public List<MealPlanner> mealPlannersByMealType(@Argument String type) {
        return mealPlannerRepository.findByMealTypeType(type);
    }
    
    @QueryMapping
    public List<MealPlanner> mealPlannersByDietType(@Argument String type) {
        return mealPlannerRepository.findByDietTypeType(type);
    }
    
    @QueryMapping
    public List<MealPlanner> mealPlannersByOption(@Argument Integer option) {
        return mealPlannerRepository.findByOption(option);
    }
    
    @MutationMapping
    public MealType createMealType(@Argument MealTypeInput input) {
        return mealPlannerService.createMealType(input);
    }
    
    @MutationMapping
    public DietType createDietType(@Argument DietTypeInput input) {
        return mealPlannerService.createDietType(input);
    }
    
    @MutationMapping
    public MealPlanner createMealPlanner(@Argument MealPlannerInput input) {
        return mealPlannerService.createMealPlanner(input);
    }
    
    @MutationMapping
    public MealPlanner updateMealPlanner(@Argument UUID id, @Argument MealPlannerInput input) {
        return mealPlannerService.updateMealPlanner(id, input);
    }
    
    @MutationMapping
    public boolean deleteMealPlanner(@Argument UUID id) {
        return mealPlannerService.deleteMealPlanner(id);
    }
}
