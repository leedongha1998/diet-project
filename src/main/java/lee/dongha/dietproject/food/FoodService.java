package lee.dongha.dietproject.food;

import lee.dongha.dietproject.food.dto.FoodSummaryDto;
import lee.dongha.dietproject.food.entity.Food;
import lee.dongha.dietproject.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<FoodSummaryDto> getFoodListByName(String name) {
        List<Food> foodList = foodRepository.findAllByName(name);
        return foodList.stream().map(FoodSummaryDto::toDto).collect(Collectors.toList());
    }

    public Double getTotalCalories(List<Long> ids) {
        List<Food> foodList = foodRepository.findAllById(ids);
        return foodList.stream().mapToDouble(f -> f.getCalorie()).sum();
    }
}
