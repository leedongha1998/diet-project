package lee.dongha.dietproject.food.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lee.dongha.dietproject.food.entity.Food;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "이름으로 음식 검색시 음식 요약 정보 DTO")
public class FoodSummaryDto {
    @Schema(description = "아이디", example = "1")
    private Long id;
    @Schema(description = "음식 이름", example = "된장국")
    private String name;
    @Schema(description = "1회 제공량", example = "1000")
    private int servingSize; // 1회 제공량 g
    @Schema(description = "칼로리", example = "234")
    private double calorie;
    @Schema(description = "음식 카테고리", example = "찌개류")
    private String category;

    @Builder
    public FoodSummaryDto(Long id, String name, int servingSize, double calorie, String category) {
        this.id = id;
        this.name = name;
        this.servingSize = servingSize;
        this.calorie = calorie;
        this.category = category;
    }

    public static FoodSummaryDto toDto(Food food) {

        return FoodSummaryDto.builder()
                .id(food.getId())
                .name(food.getName())
                .servingSize(food.getServingSize())
                .calorie(food.getCalorie())
                .category(food.getCategory())
                .build();
    }
}
