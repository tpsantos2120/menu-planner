package com.nutrily.menuplanner.specification;

import com.nutrily.menuplanner.entity.MealPlanner;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MealPlannerSpecification {

  public static Specification<MealPlanner> buildSpecification(Map<String, Object> filter) {
    Specification<MealPlanner> spec = Specification.where(null);

    if (filter == null) {
      return spec;
    }

    if (filter.containsKey("option") && filter.get("option") != null) {
      Integer option = (Integer) filter.get("option");
      spec = spec.and((root, query, cb) -> cb.equal(root.get("option"), option));
    }

    if (filter.containsKey("mealDescription") && filter.get("mealDescription") != null) {
      String mealDescription = (String) filter.get("mealDescription");
      if (StringUtils.hasText(mealDescription)) {
        spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("mealDescription")),
                        "%" + mealDescription.toLowerCase() + "%"));
      }
    }

    if (filter.containsKey("mealTypeType") && filter.get("mealTypeType") != null) {
      String mealTypeType = (String) filter.get("mealTypeType");
      if (StringUtils.hasText(mealTypeType)) {
        spec = spec.and((root, query, cb) -> {
          Join<Object, Object> mealTypeJoin = root.join("mealType");
          return cb.equal(mealTypeJoin.get("type"), mealTypeType);
        });
      }
    }

    if (filter.containsKey("dietTypeType") && filter.get("dietTypeType") != null) {
      String dietTypeType = (String) filter.get("dietTypeType");
      if (StringUtils.hasText(dietTypeType)) {
        spec = spec.and((root, query, cb) -> {
          Join<Object, Object> dietTypeJoin = root.join("dietType");
          return cb.equal(dietTypeJoin.get("type"), dietTypeType);
        });
      }
    }

    return spec;
  }
}
