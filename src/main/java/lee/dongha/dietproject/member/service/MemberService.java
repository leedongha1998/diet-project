package lee.dongha.dietproject.member.service;

import lee.dongha.dietproject.member.dto.MemberInfoDto;
import lee.dongha.dietproject.member.dto.MemberSurveyDto;
import lee.dongha.dietproject.member.entity.Member;
import lee.dongha.dietproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberInfoDto getMyInfo(String username) {
        Member findMember = memberRepository.findMemberByUsername(username);
        MemberInfoDto result = MemberInfoDto.toDto(findMember);
        return result;
    }

    public void postMySurvey(String username, MemberSurveyDto memberSurveyDto) {
        Member findMember = memberRepository.findMemberByUsername(username);
        findMember.updateMemberBySurvey(memberSurveyDto);
    }
}
