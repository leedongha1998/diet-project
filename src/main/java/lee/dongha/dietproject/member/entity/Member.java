package lee.dongha.dietproject.member.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.global.BaseEntity;
import lee.dongha.dietproject.member.dto.MemberSurveyDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String role;
    private double startWeight;
    private double weight;
    private double height;
    private double bmi;
    private int targetCalorie;
    private int breakfastRate;
    private int lunchfastRate;
    private int dinnerfastRate;
    private String gender;
    private int age;
    private double targetWeight;
    private String healthStatus;
    private int water;
    @OneToMany(mappedBy = "member")
    private List<Diet> dietList = new ArrayList<>();
    @Builder
    public Member(String name, String username, String password, String email, String role, double startWeight, double weight, double height, double bmi, int targetCalorie, int breakfastRate, int lunchfastRate, int dinnerfastRate, String gender, int age, double targetWeight, String healthStatus, int water) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.startWeight = startWeight;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.targetCalorie = targetCalorie;
        this.breakfastRate = breakfastRate;
        this.lunchfastRate = lunchfastRate;
        this.dinnerfastRate = dinnerfastRate;
        this.gender = gender;
        this.age = age;
        this.targetWeight = targetWeight;
        this.healthStatus = healthStatus;
        this.water = water;
    }

    public void updateMember(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void updateMemberBySurvey(MemberSurveyDto memberSurveyDto){
        this.age = memberSurveyDto.getAge();
        this.startWeight = memberSurveyDto.getWeight();
        this.height = memberSurveyDto.getHeight();
        this.targetCalorie = (int) memberSurveyDto.getTargetCalorie();
        this.targetWeight = memberSurveyDto.getTargetWeight();
        this.healthStatus = memberSurveyDto.getHealthStatus();
        this.water = memberSurveyDto.getWater();
        this.gender = memberSurveyDto.getGender();
    }
}
