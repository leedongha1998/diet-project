package lee.dongha.dietproject.food.entity;

import jakarta.persistence.*;
import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Food extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int servingSize; // 1회 제공량 g
    private double calorie;
    private double protein; // g
    private double carbohydrate; // 탄수화물 g
    private double fat; // 지방 g
    private double sugars; // 총 당류 g
    private double vitamin; // mg
    private String category;

    public Food(String name, double calorie, double protein, double carbohydrate, double fat) {
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }
}
