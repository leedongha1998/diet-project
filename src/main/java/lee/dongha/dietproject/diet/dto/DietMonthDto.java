package lee.dongha.dietproject.diet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DietMonthDto {
    private List<Long> ids;
    private String date;
}
