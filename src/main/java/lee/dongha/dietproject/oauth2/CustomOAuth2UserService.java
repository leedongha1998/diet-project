package lee.dongha.dietproject.oauth2;

import lee.dongha.dietproject.member.entity.Member;
import lee.dongha.dietproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {

            return null;
        }

        // 리소스 서버에서 발급받은 정보로 사용자를 특정할 아이디 값을 만듬
        String username = oAuth2Response.getProvider()+" " + oAuth2Response.getEmail();
        Member findMember = memberRepository.findMemberByUsername(username);

        if(findMember == null){
            Member member = Member.builder()
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .name(oAuth2Response.getName())
                    .role("ROLE_USER")
                    .build();

            memberRepository.save(member);

            MemberDto memberDto = new MemberDto();
            memberDto.setUsername(username);
            memberDto.setName(oAuth2Response.getName());
            memberDto.setRole("ROLE_USER");

            return new CustomOauth2User(memberDto);
        }else{
            findMember.updateMember(oAuth2Response.getEmail(),oAuth2Response.getName());
            memberRepository.save(findMember);
            MemberDto memberDto = new MemberDto();
            memberDto.setUsername(findMember.getUsername());
            memberDto.setName(oAuth2Response.getName());
            memberDto.setRole(findMember.getRole());

            return new CustomOauth2User(memberDto);
        }
    }
}
