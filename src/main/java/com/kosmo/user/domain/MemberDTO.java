package com.kosmo.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본생성
// @AllArgsConstructor // 인자생성자(잘 안쓰임)
@Setter //setXXX()
@Getter //getXXX()
@ToString //toString() 오버라이드
public class MemberDTO {
    //property
    private int idx;
    private String name;
    private String userId;
    private String userPw;
    private String email;
    private int mstate;
    private java.sql.Date indate;
    private String mstateStr;
}
