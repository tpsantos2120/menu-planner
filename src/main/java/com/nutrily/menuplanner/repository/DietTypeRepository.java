package com.nutrily.menuplanner.repository;

import com.nutrily.menuplanner.entity.DietType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.Optional;
import java.util.UUID;

@GraphQlRepository
public interface DietTypeRepository extends JpaRepository<DietType, UUID>, QueryByExampleExecutor<DietType> {
  Optional<DietType> findByType(String type);
}
