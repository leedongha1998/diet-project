package lee.dongha.dietproject.food.dto;

import lee.dongha.dietproject.food.entity.Food;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodInDietDto {
    private String name;
    private String calorie;

    public FoodInDietDto(String name, String calorie) {
        this.name = name;
        this.calorie = calorie;
    }

    public static FoodInDietDto toDto(Food food) {
        return new FoodInDietDto(food.getName(),String.valueOf(food.getCalorie()) + "Kcal");
    }
}
