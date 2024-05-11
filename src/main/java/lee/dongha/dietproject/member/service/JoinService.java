package lee.dongha.dietproject.member.service;

import lee.dongha.dietproject.member.entity.Member;
import lee.dongha.dietproject.member.repository.MemberRepository;
import lee.dongha.dietproject.member.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(SignUpDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        System.out.println("password = " + password);
        System.out.println("username = " + username);

        Boolean isExist = memberRepository.existsByUsername(username);

        if(isExist){
            return;
        }

        Member member = Member.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .role("ROLE_ADMIN")
                .build();
        memberRepository.save(member);
    }

    public String getMyInfo(String username) {
        Member findMember = memberRepository.findMemberByUsername(username);
        return findMember.getEmail();
    }
}
