package lee.dongha.dietproject.diet.entity;

import jakarta.persistence.*;
import lee.dongha.dietproject.diet.dto.DietDto;
import lee.dongha.dietproject.food.entity.Food;
import lee.dongha.dietproject.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DietFood extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Diet diet;
    @ManyToOne
    private Food food;
    public DietFood(Diet diet, Food food) {
        this.diet = diet;
        this.food = food;
    }

}
