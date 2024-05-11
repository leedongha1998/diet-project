package lee.dongha.dietproject.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lee.dongha.dietproject.jwt.JWTUtil;
import lee.dongha.dietproject.member.dto.SignUpDto;
import lee.dongha.dietproject.member.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;
    private final JWTUtil jwtUtil;

    @PostMapping("/join")
    public String joinProcess(@RequestBody SignUpDto dto){
        joinService.joinProcess(dto);
        return "ok";
    }

    @GetMapping("/")
    public String mainP(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Main Controller : " +  name;
    }

    @GetMapping("/my")
    public String myApi(HttpServletRequest request) {
        // 쿠키에서 Authorization 값을 추출
        Cookie[] cookies = request.getCookies();

        String accessToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtUtil.getUsername(accessToken);
        String emali = joinService.getMyInfo(username);

        return emali;
    }

    @GetMapping("/my-p")
    public String myApiByPostman(HttpServletRequest request){
        String token = request.getHeader("access");
        String username = jwtUtil.getUsername(token);
        String myInfo = joinService.getMyInfo(username);
        return myInfo;
    }

}
