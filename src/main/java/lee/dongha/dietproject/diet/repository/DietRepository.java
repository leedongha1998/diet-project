package lee.dongha.dietproject.diet.repository;

import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.MealTime;
import lee.dongha.dietproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DietRepository extends JpaRepository<Diet,Long> {
    @Query("select d from Diet d where d.member = :member and d.date = :targetDate order by d.mealTime desc ")
    List<Diet> findDietByMemberAndDate(@Param("member") Member member, @Param("targetDate") LocalDate targetDate);

    @Modifying
    @Query("delete from Diet d where d.member = :member and d.id in (:ids)")
    void deleteAllByIdsAndMember(List<Long> ids,Member member);

    List<Diet> findAllByMemberUsernameAndDate(String username, LocalDate localDate);
    @Query("SELECT d FROM Diet d WHERE MONTH(d.date) = :month")
    List<Diet> findAllByMonth(String month);
    @Query("select d from Diet d where d.member = :findMember and d.date = :date and d.mealTime = :mealTime")
    Diet findDietByMemberAndDateAndMealTime(Member findMember, LocalDate date, MealTime mealTime);
}
