package Spring.service;

import Spring.domain.member.MemberEntity;
import Spring.domain.member.MemberRepository;
import Spring.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입 저장
    @Transactional
    public void membersave(MemberDto memberDto) {

        memberRepository.save(memberDto.toEntity());

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
                        .address(temp.getAddress())
                        .point(temp.getPoint()).build();

                return memberDto;

            }

        }
        return null; // 없으면 NULL
    }

    // 회원가입 아이디 중복체크
    @Transactional
    public int memberfind(String id) {

        List<MemberEntity>  memberEntityList  =  memberRepository.findAll();

        for( MemberEntity temp :  memberEntityList) {

            if( temp.getMember_id().equals(id)){
                return 1;
            }
        }
        return 0;

    }

    // 회원 수정 전 인증
    @Transactional
    public MemberEntity memberinfo(Long no, String member_pw) {

        // 1. 회원 가져오기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByinfo(no, member_pw);

        if(optionalMemberEntity.isPresent()) { //.isPresent() 값이 있는지 없는지 확인할수있음.

            MemberEntity temp = optionalMemberEntity.get();

            return temp;

        } else {

            return null;

        }

    }

    // 회원 수정 처리
    @Transactional
    public int updateinfo(MemberDto modifyDto) {

        // DB에서 회원찾기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(modifyDto.getNo());

        // 찾은 후 엔티티 가져오기
        MemberEntity memberEntity = optionalMemberEntity.get();

        // 업데이트 처리
        memberEntity.modify(modifyDto);

        return 1;

    }

    // 회원 탈퇴
    @Transactional
    public int memberdelete(Long no) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(no);

        MemberEntity memberEntity = optionalMemberEntity.get();

        memberRepository.delete(memberEntity);

        return 1;

    }

    // 회원 아이디 찾기
    @Transactional
    public MemberEntity findid(String name, String birth, String phone) {

        // 1. 회원 엔티티 가져오기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findid(name, birth, phone);

        if( optionalMemberEntity.isPresent()) { // .isPresent()

            MemberEntity memberEntity = optionalMemberEntity.get();

            return memberEntity; // 받아온 정보로 조회되면 엔티티 리턴

        } else {

            return null; // 받아온 정보가 없으면 리턴 NULL

        }

    }

    // 회원 비밀번호 찾기
    @Transactional
    public MemberEntity findpw(String member_id, String birth, String email) {

        // 회원 엔티티 가져오기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findpw(member_id, birth, email);

        if(optionalMemberEntity.isPresent()) {

            MemberEntity memberEntity = optionalMemberEntity.get();

            return memberEntity;

        } else {

            return null;

        }

    }
    
    // 회원 비밀번호 찾기 정보 입력후 비밀번호 재설정
    @Transactional
    public int modifypw(Long no, String member_pw) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(no);

        MemberEntity memberEntity = optionalMemberEntity.get();

        memberEntity.update(member_pw);

        return 1;

    }
    
    // 비밀번호 찾기에서 변경할 회원정보 넘겨주기
    public MemberEntity updatepw(Long no) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(no);

        MemberEntity memberEntity = optionalMemberEntity.get();

        return memberEntity;

    }
    
    // 회원정보에서 패스워드변경 처리
    @Transactional
    public int updatepwcomplete(Long no, String member_pw) {

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(no);

        MemberEntity memberEntity = optionalMemberEntity.get();

        memberEntity.update(member_pw);

        return 1;

    }

}
