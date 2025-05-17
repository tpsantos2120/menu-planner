package com.nutrily.menuplanner.repository;

import com.nutrily.menuplanner.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.Optional;
import java.util.UUID;

@GraphQlRepository
public interface MealTypeRepository extends JpaRepository<MealType, UUID>, QueryByExampleExecutor<MealType> {
    Optional<MealType> findByType(String type);
}
