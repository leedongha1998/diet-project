package lee.dongha.dietproject.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lee.dongha.dietproject.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "마이페이지")
public class MemberInfoDto {
    @Schema(description = "닉네임",example = "천둥")
    private String username;
    @Schema(description = "이름",example = "이동하")
    private String name;
    @Schema(description = "나이",example = "27")
    private int age;
    @Schema(description = "몸무게",example = "73.9")
    private double weight;
    @Schema(description = "키",example = "183.1")
    private double height;
    @Schema(description = "BMI",example = "100.0")
    private double bmi;
    @Schema(description = "목표 칼로리",example = "1000.0kcal")
    private String targetCalorie;
    @Schema(description = "몸무게 변화",example = "1.5kg이 늘었습니다.")
    private String changeWeight;

    public MemberInfoDto(String username, String name, int age, double weight, double height, double bmi, double targetCalorie, String changeWeight) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.targetCalorie = targetCalorie+"kcal";
        this.changeWeight = changeWeight;
    }

    public static MemberInfoDto toDto(Member member) {
        String changeWeightStr = toChangeWeightStr(member);
        double bmi = Math.round((member.getWeight() / (member.getHeight() * member.getHeight())*10000) * 10.0)/10.0;
        return new MemberInfoDto(member.getUsername(),member.getName(),member.getAge(),(int)member.getWeight(), (int)member.getHeight(), bmi, member.getTargetCalorie(),changeWeightStr);
    }

    private static String toChangeWeightStr(Member member) {
        String changeWeightStr = "";

        if(member.getWeight() != 0){
            double w = member.getWeight() - member.getStartWeight();
            if(w < 0){
                changeWeightStr = w + "kg이 줄었습니다.";
            }else{
                changeWeightStr = w + "kg이 늘었습니다.";
            }
        }
        return changeWeightStr;
    }
}
