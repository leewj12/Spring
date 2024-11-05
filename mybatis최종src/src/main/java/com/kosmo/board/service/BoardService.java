package com.kosmo.board.service;

import com.kosmo.board.domain.BoardDTO;
import com.kosmo.board.domain.PagingDTO;

import java.util.List;

public interface BoardService {
    //글쓰기
    int insertBoard(BoardDTO board);
    //글목록, 검색목록
    List<BoardDTO> listBoard(PagingDTO paging);
    //총게시글 수 or 검색한 게시글 수
    int getTotalCount(PagingDTO paging);
    
    //글번호(num -PK)로 해당 글 가져오기
    BoardDTO findBoardByNum(int num);
    int updateReadnum(int num);//조회수 증가

    //글삭제
    int deleteBoardByNum(int num);
    //글수정
    int updateBoard(BoardDTO board);
}
