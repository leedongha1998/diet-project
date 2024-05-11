package lee.dongha.dietproject.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "설문 등록 DTO")
public class MemberSurveyDto {
    @Schema(description = "성별", example = "M")
    private String gender;
    @Schema(description = "나이", example = "27")
    private int age;
    @Schema(description = "키", example = "183")
    private double height;
    @Schema(description = "몸무게", example = "74")
    private double weight;
    @Schema(description = "목표 몸무게", example = "80")
    private double targetWeight;
    @Schema(description = "건강 상태", example = "다이어트 필요")
    private String healthStatus;
    @Schema(description = "수분 섭취량", example = "1")
    private int water;
    @Schema(description = "목표 칼로리", example = "1000.0")
    private double targetCalorie;
}
