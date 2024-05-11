package lee.dongha.dietproject.diet.dto;

import lee.dongha.dietproject.diet.entity.StatusDiet;
import lee.dongha.dietproject.food.dto.FoodInfoDto;
import lee.dongha.dietproject.food.entity.Food;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DietFoodInfoDto {
    private List<FoodInfoDto> foodList;
    private String date;
    private String changeWeight;
    private String memo;
    private String condition;

    public DietFoodInfoDto(List<Food> foodList, LocalDateTime date, double changeWeight, String memo, StatusDiet condition) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateStr = date.format(dateTimeFormatter);
        List<FoodInfoDto> foodInfoDtoList = foodList.stream().map(FoodInfoDto::toDto).collect(Collectors.toList());
        this.foodList = foodInfoDtoList;
        this.date = dateStr;
        this.changeWeight = String.valueOf(changeWeight) + "kg";
        this.memo = memo;
        this.condition = String.valueOf(condition);
    }
    
}
