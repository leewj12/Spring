package com.kosmo.board.domain;

import lombok.Data;
//페이징 처리 및 검색 기능을 모듈화하여 재사용 가능하도록 처리하는 객체
@Data
public class PagingDTO {
    //페이징 처리 관련 프로퍼티
    private int pageNum;//현재 보여줄 페이지 번호 (1,2,3,...)
    private int oneRecordPage=5;//한 페이지 당 보여줄 목록 개수
    private int totalCount;//총 게시글 수. DB에서 가져와 설정할 예정
    private int pageCount;//총 게시글 수와 oneRecordPage와의 연산으로 설정

    //DB에서 레코드를 끊어오기 위한 프로퍼티
    private int start;
    private int end;
    
    //페이징 블럭처리 위한 프로퍼티
    private int prevBlock;//이전 5개 (이전블럭)
    private int nextBlock;//이후 5개 (다음블럭)
    private int pagingBlock=5;//한 블럭 당 보여줄 페이지 수
    //검색관련 프로퍼티
    private String findType;//검색유형
    private String findKeyword;//검색어

    public void init(){
        if(findType==null){
            findType="";
        }
        if(findKeyword==null){
            findKeyword="";
        }

        //페이지 수 구하기
        pageCount=(totalCount-1)/oneRecordPage +1;
        if(pageNum<1){
            pageNum=1;//1페이지를 디폴트로 설정
        }
        if(pageNum>pageCount){
            pageNum=pageCount;//마지막 페이지로 설정
        }
        //DB에서 끊어서 가져올 변수값 연산하기(start, end)
        //where rn between 1 and 5 를 사용할 때는 아래와 같이
//        end=pageNum * oneRecordPage;
//        start = end - (oneRecordPage-1);

        //where rn > 0 and rn < 6
        /*pageNum       oneRecordPage       start       end
        * 1                 5               0           6
        * 2                 5               5           11
        * 3                 5               10          16
        * start =(pageNum-1)*oneRecordPage;
        * end = start +(oneRecordPage+1)
        * */
        start =(pageNum-1)*oneRecordPage;
        end  = start +(oneRecordPage+1);
        /* ******페이징 블럭 연산********************
        * pagingBlock=5;
        * prevBlock: 이전 블럭 ◀
        * nextBlok : 이후 블럭 ▶
        * pageNum
        * [1][2][3][4][5]▶ |◀ [6][7][8][9][10]▶ |◀ [11][12][13][14][15]▶ |◀[16][17][18][19][20]
        * pageNum       pagingBlock        prevBlock(이전5개)       nextBlock(이후5개)
       *  1~ 5              5                   0                   6
        * 6~10              5                   5                   11
        * 11~15             5                   10                  16
        * 16~20             5                   15                  21
        *
        * prevBlock = (pageNum-1)/pagingBlock * pagingBlock;
        * nextBlock = prevBlock + (pagingBlock+1);
        * */
        prevBlock = (pageNum-1)/pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock+1);
    }//init()----------------------------

    /**페이지 네비게이션 문자열을 반환하는 메서드
     * myctx: 컨텍스트명(/)
     * loc : board/list
     * */
    public String getPageNavi(String myctx, String loc) {
        String link=myctx+"/"+loc; // "board/list"
        String qStr="?findType="+findType+"&findKeyword="+findKeyword;
        link+=qStr;
        //link===> "/board/list?findType=1&findKeyword=abc"
        StringBuilder buf=new StringBuilder();
        buf.append("<ul class='pagination'>");

        if(prevBlock>0) {
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='"+link+"&pageNum="+prevBlock+"'>")
                    .append("Prev")
                    .append("</a>")
                    .append("</li>");
        }
        for(int i=prevBlock+1;i<=nextBlock-1 && i<=pageCount  ;i++) {
            String css=(i==pageNum)?"active":"";
            buf.append("<li class='page-item "+css+"'>")
                    .append("<a class='page-link' href='"+link+"&pageNum="+i+"'>")
                    .append(i)
                    .append("</a>")
                    .append("</li>");

        }//for------
        if(nextBlock <= pageCount) {
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='"+link+"&pageNum="+nextBlock+"'>")
                    .append("Next")
                    .append("</a>")
                    .append("</li>");
        }
        buf.append("</ul>");
        return buf.toString();
    }//----------------------------------------------



}//class//////////////////////////////////
