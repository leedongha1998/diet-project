package lee.dongha.dietproject.member;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lee.dongha.dietproject.apiPayload.BaseResponse;
import lee.dongha.dietproject.jwt.JWTUtil;
import lee.dongha.dietproject.member.entity.RefreshEntity;
import lee.dongha.dietproject.member.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static lee.dongha.dietproject.apiPayload.BaseResponseStatus.EMPTY_JWT;

/**
 * jwt 검증하고 access token 재발급
 */
@RestController
@RequiredArgsConstructor
public class ReissueController {
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @PostMapping("/reissue")
    public BaseResponse<?> reissue(HttpServletRequest request, HttpServletResponse response){
        // 아래 코드 나중에 서비스 단으로 옮기기
        // get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("refresh")){
                refresh = cookie.getValue();
            }
        }

        if(refresh == null){
            // response status code
            return new BaseResponse<>(EMPTY_JWT);
        }

        // expired check
        try {
            jwtUtil.isExpired(refresh);
        }catch (ExpiredJwtException e){
            return new BaseResponse<>(EMPTY_JWT);
        }

        // 토큰이 refresh 인지 확인
        String category = jwtUtil.getCategory(refresh);
        if(!category.equals("refresh")){
            return new BaseResponse<>(EMPTY_JWT);
        }

        // db에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if(!isExist){
            return new BaseResponse<>(EMPTY_JWT);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        String newAccess = jwtUtil.createJwt("access",username,role,600000L);
        String newRefresh = jwtUtil.createJwt("refresh",username,role,86400000L);

        // Refresh 토큰 저장 db에 기존의 refresh 토큰 삭제 후 새 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(username,newRefresh,86400000L);

        response.setHeader("access",newAccess);
        response.addCookie(createCookie("refresh",newRefresh));

        return new BaseResponse<>(HttpStatus.OK);
    }

    private void addRefreshEntity(String username, String refresh, Long expiration){
        Date date = new Date(System.currentTimeMillis() + expiration);
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());
        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key,value);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true);
//        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
