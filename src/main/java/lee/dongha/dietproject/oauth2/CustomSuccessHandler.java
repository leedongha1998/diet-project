package lee.dongha.dietproject.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lee.dongha.dietproject.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Oauth2User
        CustomOauth2User customUserDetails = (CustomOauth2User) authentication.getPrincipal();
        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt("access",username,role,60*60*60L);

        response.addCookie(createCookie("Authorization",token));
        System.out.println("token = " + token);
        response.sendRedirect("http://localhost:10000/");
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key,value);
        cookie.setMaxAge(60*60*60);
//        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
