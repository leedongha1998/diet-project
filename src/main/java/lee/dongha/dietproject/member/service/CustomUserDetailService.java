package lee.dongha.dietproject.member.service;

import lee.dongha.dietproject.member.entity.Member;
import lee.dongha.dietproject.member.repository.MemberRepository;
import lee.dongha.dietproject.member.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // db에서 조회
        Member member = memberRepository.findMemberByUsername(username);

        if(member != null){
            // UserDetails에 담아서 return하면 AutenticationManager가 검증
            return new CustomUserDetails(member);
        }

        return null;
    }
}
