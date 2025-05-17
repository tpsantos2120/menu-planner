package com.nutrily.menuplanner.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MealPlanner {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", columnDefinition = "uuid DEFAULT uuid_generate_v4()")
  private UUID id;

  @Column(name = "option")
  private Integer option;

  @Column(name = "meal_description", length = 2000)
  private String mealDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meal_type", referencedColumnName = "type", foreignKey = @ForeignKey(name = "fk_meal_type"))
  @ToString.Exclude
  private MealType mealType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "diet_type", referencedColumnName = "type", foreignKey = @ForeignKey(name = "fk_diet_type"))
  @ToString.Exclude
  private DietType dietType;

}
