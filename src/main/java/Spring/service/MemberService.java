package Spring.service;

import Spring.domain.member.MemberRepository;
import Spring.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입 저장
   /* @Transactional
    public Long membersave(MemberDto memberDto) {

        return memberRepository.save(memberDto.toEntity());

    }*/

}
