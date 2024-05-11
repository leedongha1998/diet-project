package lee.dongha.dietproject.food.repository;

import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {
    @Query("SELECT f FROM Food f WHERE f.name LIKE CONCAT('%', :name, '%')")
    List<Food> findAllByName(@Param("name") String name);
}
