package lee.dongha.dietproject.diet.dto;

import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.DietFood;
import lee.dongha.dietproject.food.entity.Food;
import lee.dongha.dietproject.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DietHomeDto {
    private String date;
    private int intakeCalories; // 섭취량
    private int remainingCalories; // 잔여 칼로리
    private int targetCalories; // 목표 칼로리
    private int protein;
    private int carbohydrate; // 탄수화물
    private int fat;

    @Builder
    public DietHomeDto(String date, int intakeCalories, int targetCalories, double protein, double carbohydrate, double fat) {
        this.date = date;
        this.intakeCalories = intakeCalories;
        this.remainingCalories = targetCalories - intakeCalories;
        this.targetCalories = targetCalories;
        this.protein = (int) protein;
        this.carbohydrate = (int) carbohydrate;
        this.fat = (int) fat;
    }

    public static DietHomeDto toDto(List<DietFood> dietFoodList, Member member) {
        return DietHomeDto.builder()
                .date(dietFoodList.get(0).getDiet().getDate().toString())
                .intakeCalories((int) dietFoodList.stream().mapToDouble(df -> df.getFood().getCalorie()).sum())
                .targetCalories(member.getTargetCalorie())
                .protein(dietFoodList.stream().mapToDouble(df -> df.getFood().getProtein()).sum())
                .carbohydrate(dietFoodList.stream().mapToDouble(df -> df.getFood().getCarbohydrate()).sum())
                .fat(dietFoodList.stream().mapToDouble(df -> df.getFood().getFat()).sum())
                .build();
    }

    public void addProtein(double protein) {
        this.protein += (int)protein;
    }

    public void addCarbohydrate(double carbohydrate) {
        this.carbohydrate += (int)carbohydrate;
    }

    public void addCalorie(double calorie) {
        this.intakeCalories += (int)calorie;
    }

    public void addFat(double fat) {
        this.fat += (int)fat;
    }
}
