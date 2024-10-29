package com.kosmo.user.service;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.exception.NoMemberException;
import com.kosmo.user.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

//컨트롤러 계층에는 @Controller, RestController
//서비스 계층에는 @Service 을 붙인다
//영속성(Persistence)계층: @Mapper(MyBatis) / @Repository(JPA)
@Service
public class MemberServiceImpl implements MemberService{

    //final 변수로 선언해야 생성자 인젝션 가능
    private final MemberMapper userMapper;

    //생성자 인젝션 ==> MemberMapper 를 구현한 객체를 생성자의 인자로 넘긴다
    public MemberServiceImpl(MemberMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int insertMember(MemberDTO user) {
        return userMapper.insertMember(user);
        //스프링은 namespace가 com.kosmo.user.mapper.MemberMapper로 되어있는
        // xml 파일을 찾는다. 찾았다면 id가 insertMember로 되어 있는 SQL문을
        // 찾아 실행시킨다.
    }

    @Override
    public List<MemberDTO> listMember() {
        return List.of();
    }

    @Override
    public MemberDTO findMemberByUserId(String userId) {
        return null;
    }

    @Override
    public MemberDTO findMemberByIdx(int idx) {
        return null;
    }

    @Override
    public int deleteMemberByIdx(int idx) {
        return 0;
    }

    @Override
    public int updateMember(MemberDTO user) {
        return 0;
    }

    @Override
    public boolean idCheck(String userId) {
        return false;
    }

    @Override
    public MemberDTO loginCheck(MemberDTO tmpUser) throws NoMemberException {
        return null;
    }
}
