package com.nutrily.menuplanner.repository;

import com.nutrily.menuplanner.entity.MealPlanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;

@GraphQlRepository
public interface MealPlannerRepository extends JpaRepository<MealPlanner, UUID>, QueryByExampleExecutor<MealPlanner>, JpaSpecificationExecutor<MealPlanner> {

  // Find meal planners by meal type's type field
  @Query("SELECT m FROM MealPlanner m WHERE m.mealType.type = :type")
  List<MealPlanner> findByMealTypeType(@Param("type") String type);

  // Find meal planners by diet type's type field
  @Query("SELECT m FROM MealPlanner m WHERE m.dietType.type = :type")
  List<MealPlanner> findByDietTypeType(@Param("type") String type);

  // Find meal planners by option
  List<MealPlanner> findByOption(Integer option);
}
