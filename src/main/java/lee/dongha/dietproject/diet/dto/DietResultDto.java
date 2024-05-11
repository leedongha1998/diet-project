package lee.dongha.dietproject.diet.dto;

import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.food.dto.FoodInDietDto;
import lee.dongha.dietproject.food.entity.Food;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DietResultDto {
    private String date;
    private List<FoodInDietDto> foods;
    private String totalCalorie;
    private String changeWeight;
    private String memo;
    private String condition;

    @Builder
    public DietResultDto(String date, List<FoodInDietDto> foods, String totalCalorie, String changeWeight, String memo, String condition) {
        this.date = date;
        this.foods = foods;
        this.totalCalorie = totalCalorie;
        this.changeWeight = changeWeight;
        this.memo = memo;
        this.condition = condition;
    }

    public static DietResultDto toDto(Diet diet, List<Food> foodList) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        double totalCal = foodList.stream().mapToDouble(Food::getCalorie).sum();
        String totalCalWithUnit = String.format("%.2fKcal", totalCal);

        return DietResultDto.builder()
                .changeWeight(String.valueOf(diet.getChangeWeight()) + "kg")
                .memo(diet.getMemo())
                .condition(diet.getStatus().toString())
                .totalCalorie(totalCalWithUnit)
                .date(diet.getDate().format(dateTimeFormatter).toString())
                .foods(foodList.stream().map(FoodInDietDto::toDto).collect(Collectors.toList()))
                .build();
    }
}
