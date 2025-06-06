package com.nutrily.menuplanner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "meal_type",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_meal_type", columnNames = {"type"})
        })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MealType implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", columnDefinition = "uuid DEFAULT uuid_generate_v4()")
  private UUID id;

  @Column(name = "type", length = 50)
  private String type;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "mealType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @ToString.Exclude
  private Set<MealPlanner> meals;

}
