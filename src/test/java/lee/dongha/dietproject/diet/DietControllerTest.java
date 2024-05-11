package lee.dongha.dietproject.diet;

import lee.dongha.dietproject.diet.dto.DietDto;
import lee.dongha.dietproject.diet.entity.Diet;
import lee.dongha.dietproject.diet.entity.MealTime;
import lee.dongha.dietproject.diet.entity.StatusDiet;
import lee.dongha.dietproject.diet.repository.DietRepository;
import lee.dongha.dietproject.diet.service.DietService;
import lee.dongha.dietproject.food.entity.Food;
import lee.dongha.dietproject.food.repository.FoodRepository;
import lee.dongha.dietproject.member.entity.Member;
import lee.dongha.dietproject.member.repository.MemberRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DietControllerTest {
    @Autowired
    DietRepository dietRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DietService dietService;

    @Test
    public void dietHomeTest() throws Exception{
        //given
//        Member findMember = memberRepository.findMemberByUsername("이동하");
//        LocalDate localDate = LocalDate.of(2024,03,25);
//        Diet diet = new Diet(localDate,5.0,"메모", StatusDiet.LOW, MealTime.DINNER,findMember);
//        //when
//
//        //then
    }
}