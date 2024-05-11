package lee.dongha.dietproject.diet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lee.dongha.dietproject.apiPayload.BaseException;
import lee.dongha.dietproject.apiPayload.BaseResponse;
import lee.dongha.dietproject.diet.dto.*;
import lee.dongha.dietproject.food.dto.FoodInfoDto;
import lee.dongha.dietproject.diet.service.DietService;
import lee.dongha.dietproject.jwt.JWTUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "dietController", description = "식단 관련 컨트롤러")
@RestController
@RequestMapping("/diet")
@RequiredArgsConstructor
public class DietController {
    private final DietService dietService;
    private final JWTUtil jwtUtil;
    @PostMapping()
    @Operation(summary = "식단 등록 api", description = "식단 등록 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request")
    })
    public BaseResponse postDiet(HttpServletRequest request,@RequestBody DietDto dietDto){
        String token = request.getHeader("access");
        String username = jwtUtil.getUsername(token);
        try {
            dietService.postDiet(username, dietDto);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse("ok");
    }

    @Operation(summary = "날짜로 식단 가져오는 api", description = "날짜로 식단 가져오는 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "404",description = "Not Found"),
    })
    @GetMapping("/date/{date}")
    public BaseResponse<DietDateDto> getDiet(HttpServletRequest request,@PathVariable @Parameter(name = "date",description = "검색 날짜", example = "2024-01-01") String date){
        String token = request.getHeader("access");
        String username = jwtUtil.getUsername(token);
        DietDateDto result = dietService.getDiet(username,date);
        return new BaseResponse<>(result);
    }

    @Operation(summary = "월(month)로 식단 가져오는 api", description = "날짜로 식단 가져오는 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = BaseResponse.class))}),
            @ApiResponse(responseCode = "404",description = "Not Found"),
    })
    @GetMapping("/month/{month}")
    public BaseResponse<List<DietHomeDto>> getDietHome(HttpServletRequest request, @PathVariable String month){
        String token = request.getHeader("access");
        String username = jwtUtil.getUsername(token);
        List<DietHomeDto> result = dietService.getDietHome(month,username);
        return new BaseResponse<>(result);
    }
//
//    @PostMapping("/{id}")
//    public BaseResponse updateDiet(HttpServletRequest request,@RequestBody DietDto dietDto,@PathVariable Long id){
//        String token = request.getHeader("access");
//        String username = jwtUtil.getUsername(token);
//        dietService.updateDiet(username,dietDto,id);
//        return new BaseResponse("수정");
//    }
//
//    @GetMapping("/{id}")
//    public BaseResponse<DietFoodInfoDto> getDietInfo(HttpServletRequest request,@PathVariable Long id){
//        String token = request.getHeader("access");
//        String username = jwtUtil.getUsername(token);
//        DietFoodInfoDto result = dietService.getDietInfo(username,id);
//        return new BaseResponse<>(result);
//    }
//
//    @DeleteMapping("/{ids}")
//    public BaseResponse deleteDiets(HttpServletRequest request,@PathVariable List<Long> ids){
//        String token = request.getHeader("access");
//        String username = jwtUtil.getUsername(token);
//        dietService.deleteDiets(username,ids);
//        return new BaseResponse("삭제");
//    }
//
//    @GetMapping("/month/{month}")
//    public BaseResponse<List<DietMonthDto>> getDietonMonth(HttpServletRequest request,@PathVariable String month){
//        String token = request.getHeader("access");
//        String username = jwtUtil.getUsername(token);
//        List<DietMonthDto> result = dietService.getDietOnMonth(username,month);
//        return new BaseResponse<>(result);
//    }
//
//    @GetMapping("/{date}")
//    public BaseResponse<DietHomeDto> getHome(HttpServletRequest request,@PathVariable String date){
//        String token = request.getHeader("access");
//        String username = jwtUtil.getUsername(token);
//        DietHomeDto result = dietService.getHome(username,date);
//        return new BaseResponse<>(result);
//    }
}
