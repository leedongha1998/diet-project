package lee.dongha.dietproject.food.dto;

import lee.dongha.dietproject.food.entity.Food;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodInfoDto {
    private String calorie;
    private String carbohydrate; // 탄수화물
    private String fat;
    private String serving_size;
    private String sugars;
    private String vitamin;
    private String name;
    private String category;

    public FoodInfoDto(String calorie, String carbohydrate, String fat, String serving_size, String sugars, String vitamin, String name, String category) {
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.serving_size = serving_size;
        this.sugars = sugars;
        this.vitamin = vitamin;
        this.name = name;
        this.category = category;
    }

    public static FoodInfoDto toDto(Food food){
        return new FoodInfoDto(String.valueOf(food.getCalorie()) +"Kcal",String.valueOf(food.getCarbohydrate())+"g",String.valueOf(food.getFat()) +"g",String.valueOf(food.getServingSize()) + "g",String.valueOf(food.getSugars()) + "g",String.valueOf(food.getVitamin())+"g",food.getName(),food.getCategory());
    }
}
