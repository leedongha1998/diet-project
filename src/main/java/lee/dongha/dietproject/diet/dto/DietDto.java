package lee.dongha.dietproject.diet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.MealTime;
import lee.dongha.dietproject.diet.entity.StatusDiet;
import lee.dongha.dietproject.food.dto.FoodIdDto;
import lee.dongha.dietproject.food.entity.Food;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Schema(title = "식단 등록 DTO")
public class DietDto {
    @Schema(description = "날짜", example = "")
    private String date;
    @Schema(description = "음식 아이디 리스트", example = "[1,2,3]")
    private List<Long> foodList;

//    @Schema(description = "총 칼로리", example = "1300")
//    private double totalCalorie;
    @Schema(description = "현재 체중", example = "80")
    private double weight;
    @Schema(description = "메모", example = "오늘 배가 아팠다.")
    private String memo;
    @Schema(description = "컨디션", example = "LOW")
    private String condition;
    @Schema(description = "아점저 표시 0 : 아침, 1 : 점심, 2: 저녁",example = "0")
    private MealTime mealTime;
    @Schema(description = "이미지")
    private String image;

    @Builder
    public DietDto(String date, List<Long> foodList, double weight, String memo, String condition, String image) {
        this.date = date;
        this.foodList = foodList;
//        this.totalCalorie = totalCalorie;
        this.weight = weight;
        this.memo = memo;
        this.condition = condition;
        this.image = image;
    }

    public static DietDto toDto(Diet diet,List<Food> foodList){
        return DietDto.builder()
                .date(diet.getDate().toString())
                .foodList(foodList.stream().map(f -> f.getId()).collect(Collectors.toList()))
//                .totalCalorie(foodList.stream().mapToDouble(f -> f.getCalorie()).sum())
                .condition(diet.getStatus().getKorean())
                .build();
    }
}
