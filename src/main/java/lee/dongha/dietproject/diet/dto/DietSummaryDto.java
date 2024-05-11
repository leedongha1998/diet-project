package lee.dongha.dietproject.diet.dto;

import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.DietFood;
import lee.dongha.dietproject.diet.entity.MealTime;
import lee.dongha.dietproject.food.entity.Food;
import lee.dongha.dietproject.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DietSummaryDto {
    private String mealTime;
    private int totalCal; // 전체 칼로리
    private int targetCalories;
    private double protein;
    private double carbohydrate;
    private double fat;
    private List<String> foodNameList;
    private String image;
    @Builder
    public DietSummaryDto(String mealTime, int totalCal, int targetCalories, double protein, double carbohydrate, double fat, List<String> foodNameList,String image) {
        this.mealTime = mealTime;
        this.totalCal = totalCal;
        this.targetCalories = targetCalories;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.foodNameList = foodNameList;
        this.image = image;
    }

    public static DietSummaryDto toDto(List<DietFood> dietFoodList, Member member){
        return DietSummaryDto.builder()
                .mealTime(dietFoodList.get(0).getDiet().getMealTime().getKorean())
                .protein(dietFoodList.stream().mapToDouble(df -> df.getFood().getProtein()).sum())
                .carbohydrate(dietFoodList.stream().mapToDouble(df -> df.getFood().getCarbohydrate()).sum())
                .fat(dietFoodList.stream().mapToDouble(df -> df.getFood().getFat()).sum())
                .foodNameList(dietFoodList.stream().map(df -> df.getFood().getName()).collect(Collectors.toList()))
                .targetCalories((int) (member.getTargetCalorie() * getMealTimePercent(dietFoodList.get(0).getDiet(),member)))
                .totalCal(dietFoodList.stream().mapToInt(df -> (int) df.getFood().getCalorie()).sum())
                .image(dietFoodList.get(0).getDiet().getImage())
                .build();
    }

    private static double getMealTimePercent(Diet diet,Member member){
        double percent;
        if(diet.getMealTime() == MealTime.BREAKFAST){
            percent = (double) member.getBreakfastRate() / 100;
            return percent;
        } else if (diet.getMealTime() == MealTime.LUNCH) {
            percent = (double)member.getLunchfastRate() / 100;
            return percent;
        }else{
            percent = (double) member.getDinnerfastRate() / 100;
            return percent;
        }
    }
}
