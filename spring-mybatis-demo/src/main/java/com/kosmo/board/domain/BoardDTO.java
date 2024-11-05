package com.kosmo.board.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardDTO {
    //property(멤버변수(서버))
    private int newImg;//24시간 이내에 쓴 글일 경우 값은 0으로 들어옴

    private int num;
    private String userId;
    private String passwd;

    private String title;
    private String content;

    private Timestamp wdate;
    private int readnum;

    private String fileName; //물리적 파일명 (uuid_파일명.확장자)
    private String originFilename; //원본 파일명(파일명.확장자)
    private long fileSize; //첨부 파일크기
    private String old_fileName; // 예전에 첨부했던 파일명 => 글 수정시 필요함
    private String mode; //글쓰기(write), 글수정(edit)
}