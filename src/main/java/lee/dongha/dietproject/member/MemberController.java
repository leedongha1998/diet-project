package lee.dongha.dietproject.member;

import jakarta.servlet.http.HttpServletRequest;
import lee.dongha.dietproject.apiPayload.BaseResponse;
import lee.dongha.dietproject.jwt.JWTUtil;
import lee.dongha.dietproject.member.dto.MemberInfoDto;
import lee.dongha.dietproject.member.dto.MemberSurveyDto;
import lee.dongha.dietproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @GetMapping("/my-page")
    public BaseResponse<MemberInfoDto> getMyInfo(HttpServletRequest request){
        String token = request.getHeader("access");
        String username = jwtUtil.getUsername(token);
        MemberInfoDto result = memberService.getMyInfo(username);
        return new BaseResponse<>(result);
    }

    @PostMapping("/survey")
    public BaseResponse postMySurvey(HttpServletRequest request, MemberSurveyDto memberSurveyDto){
        String token = request.getHeader("access");
        String username = jwtUtil.getUsername(token);
        memberService.postMySurvey(username,memberSurveyDto);
        return new BaseResponse<>("ok");
    }
}
