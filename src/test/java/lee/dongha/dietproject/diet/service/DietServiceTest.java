//package lee.dongha.dietproject.diet.service;
//
//import lee.dongha.dietproject.diet.dto.DietDateDto;
//import lee.dongha.dietproject.diet.dto.DietDto;
//import lee.dongha.dietproject.diet.dto.DietHomeDto;
//import lee.dongha.dietproject.diet.dto.DietSummaryDto;
//import lee.dongha.dietproject.diet.entity.Diet;
//import lee.dongha.dietproject.diet.entity.DietFood;
//import lee.dongha.dietproject.diet.entity.MealTime;
//import lee.dongha.dietproject.diet.entity.StatusDiet;
//import lee.dongha.dietproject.diet.repository.DietFoodRepository;
//import lee.dongha.dietproject.diet.repository.DietRepository;
//import lee.dongha.dietproject.food.entity.Food;
//import lee.dongha.dietproject.food.repository.FoodRepository;
//import lee.dongha.dietproject.member.entity.Member;
//import lee.dongha.dietproject.member.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.atIndex;
//import static org.junit.jupiter.api.Assertions.*;
////@DataJpaTest
//class DietServiceTest {
//
////    @Autowired
////    DietRepository dietRepository;
////    @Autowired
////    FoodRepository foodRepository;
////    @Autowired
////    MemberRepository memberRepository;
////    @Autowired
////    DietFoodRepository dietFoodRepository;
////
////    @BeforeEach
////    public void beforeTest() throws Exception{
////        LocalDate localDate = LocalDate.of(2024,03,27);
////        LocalDate localDate2 = LocalDate.of(2024,03,28);
////        Member findMember = memberRepository.save(new Member("이동하", "이동하", "$2a$10$.4Or0ykR5hiSdsz4QhbKFuAXjWAX7yNUO35rIBSa1iFdBB3uIKRPS", "eedongha@naver.com", "Role_User", 70.0, 80.1, 100.0, 1000,30,40,30,30,"Mail",27,85.0,"Good",5));
////        Diet diet = new Diet(localDate,"image",72.4,"메모1", StatusDiet.MID, MealTime.DINNER,findMember);
////        Diet diet2 = new Diet(localDate2,"image",72.4,"메모2", StatusDiet.MID, MealTime.DINNER,findMember);
////        dietRepository.save(diet);
////        dietRepository.save(diet2);
////        Food food1 = new Food("음식1",100.0,100.0,100.0,100.0);
////        Food food2 = new Food("음식2",200.0,200.0,200.0,200.0);
////        Food food3 = new Food("음식3",300.0,300.0,300.0,300.0);
////        Food food4 = new Food("음식 잌이라",400.0,400.0,400.0,400.0);
////
////
////        foodRepository.save(food1);
////        foodRepository.save(food2);
////        foodRepository.save(food3);
////        foodRepository.save(food4);
////
////        List<Food> foodList = foodRepository.findAll();
////
////        dietFoodRepository.save(new DietFood(diet2,food3));
////        dietFoodRepository.save(new DietFood(diet2,food4));
////
////        foodList.forEach(f -> {
////            dietFoodRepository.save(new DietFood(diet, f));
////        });
////
////    }
////
////    @Test
////    public void 식단등록() throws Exception{
////        //given
////
////    }
////
////    @Test
////    public void 음식_찾기() throws Exception{
////        //given
////        //when
////        List<Food> foodList = foodRepository.findAllByName("음식");
////
////        //then
////        assertThat(foodList.size()).isEqualTo(4);
////    }
////
////
////    @Test
////    public void 식단_홈() throws Exception{
////        // 홈
////        // 해당 월에 날짜별 섭취 칼로리/목표 칼로리, 탄수화물 단백질 비율
////
////        //given
////        Member member = memberRepository.findMemberByUsername("이동하");
////        String month = "03";
////        List<Diet> dietList = dietRepository.findAllByMonth(month);
////        List<DietHomeDto> result = new ArrayList<>();
////
////        dietList.forEach(d -> {
////            List<DietFood> allByDiet = dietFoodRepository.findAllByDiet(d);
////            result.add(DietHomeDto.toDto(allByDiet,member));
////        });
////
////        assertThat(result.size()).isEqualTo(2);
////        assertThat(result.get(1).getDate().toString()).isEqualTo("2024-03-28");
////
////    }
////
////
////    // 해당 날짜 식단
////    // 식단 리스트(사진,음식 이름, 저녁, 탄단지 비율, 총 칼로리, 컨디션 표시), 아랫단(소모 칼로리/목표 칼로리, 아침 점심 저녁)
////    @Test
////    public void 날짜로_식단_가져오기() throws Exception{
////        //given
//////        Member findMember = memberRepository.findMemberByUsername("이동하");
//////        LocalDate localDate = LocalDate.of(2024,03,27);
//////        //when
//////        List<Diet> dietList = dietRepository.findAllByMemberUsernameAndDate(findMember.getUsername(),localDate);
//////        List<DietSummaryDto> dietSummaryDtoList = new ArrayList<>();
//////        dietList.forEach(d -> {
//////            List<DietFood> allByDiet = dietFoodRepository.findAllByDiet(d);
//////            dietSummaryDtoList.add(DietSummaryDto.toDto(allByDiet, findMember));
//////        });
//////        DietDateDto result = DietDateDto.toDto(dietSummaryDtoList, findMember);
//////
//////        //then
//////        assertThat(result.getDietSummaryDtoList().size()).isEqualTo(1);
//////        assertThat(result.getIntakeCaloreis()).isEqualTo(1000);
//////        assertThat(result.getTargetCalories()).isEqualTo(1000);
//////
//////        assertThat(result.getDietSummaryDtoList().get(0).getTargetCalories()).isEqualTo(1000);
////    }
//}