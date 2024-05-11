package lee.dongha.dietproject.food;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lee.dongha.dietproject.apiPayload.BaseResponse;
import lee.dongha.dietproject.food.dto.FoodSummaryDto;
import lee.dongha.dietproject.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "foodController", description = "음식 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;
    private final JWTUtil jwtUtil;

    @GetMapping("name/{name}")
    @Operation(summary = "음식 찾기", description = "이름으로 음식 찾기 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "SUCCESS",content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request")
    })
    public BaseResponse<List<FoodSummaryDto>> getFoodListByName(@PathVariable String name){
        List<FoodSummaryDto> result = foodService.getFoodListByName(name);
        return new BaseResponse<>(result);
    }

    @GetMapping("/calories/{ids}")
    @Operation(summary = "식단 음식들 총 칼로리", description = "식단 음식들 총 칼로리 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "SUCCESS",content = {@Content(schema = @Schema(implementation = BaseResponse.class),examples = @ExampleObject(name = "칼로리 총합", value ="result : 350.0"))}),
            @ApiResponse(responseCode = "400",description = "Bad Request")
    })
    public BaseResponse<Double> getTotalCalories(@PathVariable @Schema(description = "음식 아이디들", example = "1,2,3") List<Long> ids){
        Double result = foodService.getTotalCalories(ids);
        return new BaseResponse<>(result);
    }


}
