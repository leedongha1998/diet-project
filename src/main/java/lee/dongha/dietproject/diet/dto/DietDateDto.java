package lee.dongha.dietproject.diet.dto;

import lee.dongha.dietproject.diet.entity.DietFood;
import lee.dongha.dietproject.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DietDateDto {
    private List<DietSummaryDto> dietSummaryDtoList;
    private int targetCalories;
    private int intakeCaloreis; // 아침 점심 저녁 총 섭취 칼로리

    public DietDateDto(List<DietSummaryDto> dietSummaryDtoList, int targetCalories, int intakeCaloreis) {
        this.dietSummaryDtoList = dietSummaryDtoList;
        this.targetCalories = targetCalories;
        this.intakeCaloreis = intakeCaloreis;
    }

    public static DietDateDto toDto(List<DietSummaryDto> dietSummaryDtoList,Member member) {
        return new DietDateDto(dietSummaryDtoList, (int) member.getTargetCalorie(),dietSummaryDtoList.stream().mapToInt(s -> s.getTotalCal()).sum());
    }
}










