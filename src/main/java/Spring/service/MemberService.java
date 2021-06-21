package Spring.service;

import Spring.domain.member.MemberEntity;
import Spring.domain.member.MemberRepository;
import Spring.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입 저장
    @Transactional
    public Long membersave(MemberDto memberDto) {

        return memberRepository.save(memberDto.toEntity()).getNo();

    }

    // 회원 로그인 처리
    @Transactional
    public MemberDto memberlogin(MemberDto logindto) {

        // 1. 모든 회원 가져오기
        List<MemberEntity> memberEntityList = memberRepository.findAll();

        // 2. 로그인에 입력된 아이디와 비밀번호 찾기
        for(MemberEntity temp : memberEntityList) {

            if(temp.getMember_id().equals(logindto.getMember_id())
                && temp.getMember_pw().equals(logindto.getMember_pw())) {

                // 3. 찾은 DTO 반환
                MemberDto memberDto = MemberDto.builder()
                        .no(temp.getNo())
                        .member_id(temp.getMember_id())
                        .member_pw(temp.getMember_pw())
                        .name(temp.getName())
                        .gender(temp.getGender())
                        .birth(temp.getBirth())
                        .phone(temp.getPhone())
                        .email(temp.getEmail())
                        .address(temp.getAddress()).build();

                return memberDto;

            }

        }
        return null; // 없으면 NULL
    }

    @Transactional
    public int memberfind(String id) {

        List<MemberEntity>  memberEntityList  =  memberRepository.findAll();

        for( MemberEntity temp :  memberEntityList) {

            if( temp.getMember_id().equals(id)){return 1;};
        }
        return 0;

    }


}
