package lee.dongha.dietproject.diet.service;

import lee.dongha.dietproject.apiPayload.BaseException;
import lee.dongha.dietproject.apiPayload.BaseResponseStatus;
import lee.dongha.dietproject.diet.dto.*;
import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.DietFood;
import lee.dongha.dietproject.diet.repository.DietFoodRepository;
import lee.dongha.dietproject.diet.repository.DietRepository;
import lee.dongha.dietproject.food.entity.Food;
import lee.dongha.dietproject.food.repository.FoodRepository;
import lee.dongha.dietproject.member.entity.Member;
import lee.dongha.dietproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DietService {
    private final DietRepository dietRepository;
    private final FoodRepository foodRepository;
    private final MemberRepository memberRepository;
    private final DietFoodRepository dietFoodRepository;
    @Transactional
    public void postDiet(String username, DietDto dietDto) throws BaseException {
        Member findMember = memberRepository.findMemberByUsername(username);

        List<Food> foodList = foodRepository.findAllById(dietDto.getFoodList());

        Diet diet = Diet.toEntity(dietDto,findMember);
        Diet findDiet = dietRepository.findDietByMemberAndDateAndMealTime(findMember, LocalDate.parse(dietDto.getDate()), dietDto.getMealTime());

        if(findDiet != null){
            throw new BaseException(BaseResponseStatus.EXIST_DIET);
        }
        dietRepository.save(diet);

        foodList.forEach(f -> {
            dietFoodRepository.save(new DietFood(diet, f));
        });
    }


    public DietDateDto getDiet(String username, String date) {
        LocalDate localDate = LocalDate.parse(date);
        Member findMember = memberRepository.findMemberByUsername(username);
        List<Diet> dietList = dietRepository.findAllByMemberUsernameAndDate(findMember.getUsername(),localDate);

        List<DietSummaryDto> dietSummaryDtoList = new ArrayList<>();
        dietList.forEach(d -> {
            List<DietFood> allByDiet = dietFoodRepository.findAllByDiet(d);
            dietSummaryDtoList.add(DietSummaryDto.toDto(allByDiet, findMember));
        });

        DietDateDto result = DietDateDto.toDto(dietSummaryDtoList, findMember);

        return result;
    }

    public List<DietHomeDto> getDietHome(String month, String username) {
        // 해당일 탄단지, 먹은 칼로리, 타겟 칼로리
        // 지금 해당일에 식단 여러개 있으면 합쳐서 안나오고 따로 나옴

        // 사용자 정보 조회
        Member findMember = memberRepository.findMemberByUsername(username);

        // 해당 달에 대한 모든 식단 조회
        List<Diet> dietList = dietRepository.findAllByMonth(month);

        // 날짜별로 식단을 그룹화
        Map<LocalDate, List<Diet>> dietsByDate = dietList.stream()
                .collect(Collectors.groupingBy(Diet::getDate));

        List<DietHomeDto> result = new ArrayList<>();

        // 날짜별로 식단 정보를 합산하여 DTO 생성
        dietsByDate.forEach((date, diets) -> {

            DietHomeDto dto = DietHomeDto.builder()
                    .date(String.valueOf(date))
                    .targetCalories(findMember.getTargetCalorie())
                    .intakeCalories(0)
                    .protein(0)
                    .fat(0)
                    .carbohydrate(0)
                    .build();

            // 각 식단별로 섭취한 음식의 영양소 합산
            for (Diet diet : diets) {
                List<DietFood> dietFoods = dietFoodRepository.findAllByDiet(diet);
                for (DietFood dietFood : dietFoods) {
                    dto.addProtein(dietFood.getFood().getProtein());
                    dto.addCarbohydrate(dietFood.getFood().getCarbohydrate());
                    dto.addCalorie(dietFood.getFood().getCalorie());
                    dto.addFat(dietFood.getFood().getFat());
                }
            }

            dto.setRemainingCalories(dto.getTargetCalories() - dto.getIntakeCalories());

            // 각 날짜별로 계산된 결과를 리스트에 추가
            result.add(dto);
        });

        Collections.sort(result, Comparator.comparing(DietHomeDto::getDate));

        return result;
    }

//    @Transactional
//    public void updateDiet(String username, DietDto dietDto, Long id) {
//        Member findMember = memberRepository.findMemberByUsername(username);
//        Diet findDiet = dietRepository.findById(id).get();
//        dietFoodRepository.deleteAllByDiet(findDiet);
//
//        List<Food> foods = foodRepository.findAllById(dietDto.getFoodList());
//        foods.forEach(f -> {
//            DietFood dietFood = new DietFood(findDiet,f);
//            dietFoodRepository.save(dietFood);
//        });
//
//        findDiet.updateDiet(dietDto);
//        dietRepository.save(findDiet);
//    }
//
//    public DietFoodInfoDto getDietInfo(String username, Long id) {
//        Member findMember = memberRepository.findMemberByUsername(username);
//        Diet diet = dietRepository.findByIdAndMember(id,findMember);
//        List<DietFood> dietFoods = dietFoodRepository.findAllByDiet(diet);
//        List<Food> foods = dietFoods.stream().map(df -> df.getFood()).collect(Collectors.toList());
//        DietFoodInfoDto result = new DietFoodInfoDto(foods,diet.getDate(),diet.getChangeWeight(),diet.getMemo(),diet.getStatus());
//        return result;
//    }
//    @Transactional
//    public void deleteDiets(String username, List<Long> ids) {
//        Member findMember = memberRepository.findMemberByUsername(username);
//        dietFoodRepository.deleteAllByDietIds(ids);
//        dietRepository.deleteAllByIdsAndMember(ids,findMember);
//    }
//
//    public List<DietMonthDto> getDietOnMonth(String username, String month) {
//
////        dietRepository.findAllByMemberUsernameAndDateBetween(username,)
//        return null;
//    }
//
//    public DietHomeDto getHome(String username, String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDateTime targetDate = LocalDate.parse(date, formatter).atTime(LocalTime.MIN);
//        LocalDateTime targetDate2 = targetDate.plusDays(1);
//
//        Member findMember = memberRepository.findMemberByUsername(username);
//        List<Diet> dietList = dietRepository.findDietByMemberAndDate(findMember, targetDate,targetDate2);
//
//        DietHomeDto dietHomeDto = new DietHomeDto();
//        dietList.forEach(diet -> {
//            List<DietFood> dietFoods = dietFoodRepository.findAllByDiet(diet);
//            DietSummaryDto dietSummaryDto = new DietSummaryDto();
//            dietSummaryDto.setFoodNameList();
//            dietFoods.forEach(dietFood -> foodList.add(foodRepository.findById(dietFood.getFood().getId()).get()));
//            DietResultDto dietResultDto = DietResultDto.toDto(diet,foodList);
//            result.add(dietResultDto);
//        });
//
//        dietHomeDto.toDto()
//
//        return result;
//    }
}
