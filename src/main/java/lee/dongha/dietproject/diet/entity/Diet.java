package lee.dongha.dietproject.diet.entity;

import jakarta.persistence.*;
import lee.dongha.dietproject.diet.dto.DietDto;
import lee.dongha.dietproject.global.BaseEntity;
import lee.dongha.dietproject.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diet extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String image;
    private double changeWeight;
    private String memo;
    private StatusDiet status;
    @Enumerated(EnumType.STRING)
    private MealTime mealTime;
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "diet")
    private List<DietFood> dietFoodList = new ArrayList<>();

    public Diet(LocalDate date, String image, double changeWeight, String memo, StatusDiet status, MealTime mealTime, Member member) {
        this.date = date;
        this.image = image;
        this.changeWeight = changeWeight;
        this.memo = memo;
        this.status = status;
        this.mealTime = mealTime;
        this.member = member;
    }

    public static Diet toEntity(DietDto dietDto, Member member) {
        LocalDate date = LocalDate.parse(dietDto.getDate());
        StatusDiet statusDiet = StatusDiet.valueOf(dietDto.getCondition());
        return new Diet(date,dietDto.getImage(),dietDto.getWeight(),dietDto.getMemo(),statusDiet,dietDto.getMealTime(),member);
    }

    public void updateDiet(DietDto dietDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dietDto.getDate(),formatter);
        this.date = date;
        this.memo = dietDto.getMemo();
        this.changeWeight = dietDto.getWeight();
        this.status = StatusDiet.valueOf(dietDto.getCondition());
    }

}
