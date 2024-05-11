package lee.dongha.dietproject.diet.repository;

import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.DietFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietFoodRepository extends JpaRepository<DietFood,Long> {
    List<DietFood> findAllByDiet(Diet diet);
    void deleteAllByDiet(Diet diet);
    @Modifying
    @Query("delete from DietFood df where df.diet.id in (:ids)")
    void deleteAllByDietIds(List<Long> ids);
}
