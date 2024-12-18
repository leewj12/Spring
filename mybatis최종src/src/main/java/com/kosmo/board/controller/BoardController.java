package com.kosmo.board.controller;

import com.kosmo.board.domain.BoardDTO;
import com.kosmo.board.domain.PagingDTO;
import com.kosmo.board.service.BoardService;
import com.kosmo.common.util.CommonUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class BoardController {
    //Field Injection
    @Resource(name="boardServiceImpl")
    //byName 빈의 이름이 boardServiceImpl객체를 주입
    private BoardService boardService;

    //@Autowired
    @Resource(name="commonUtil")
    private CommonUtil util;

    @GetMapping("/board/form")
    public String boardForm(){
        log.info("boardService={}", boardService);
        return "board/boardWrite";
    }

    @PostMapping("/user/write")
    public String boardWrite(BoardDTO board, Model model,
                             @RequestParam("mfileName") MultipartFile mfileName,
                             HttpServletRequest req){
        log.info("board={}", board);
        log.info("mfileName={}", mfileName);
        String msg="test";
        String loc="/board/list";
        //1. 유효성 체크
        if(board.getUserId().trim().isBlank()
                ||board.getPasswd().trim().isBlank()){
            //return "redirect:/board/form";
            return util.addMsgBack(model,"글쓴이와 비밀번호는 입력해야 해요");
        }
        //2. 파일 업로드
        //1) 업로드할 디렉토리 절대경로 얻기
        String upDir=req.getServletContext().getRealPath("/board_upload");
        log.info("upDir={}",upDir);
        File dir=new File(upDir);
        if(!dir.exists()){//디렉토리가 없다면
            dir.mkdirs();//업로드 디렉토리 생성
        }
        if(! mfileName.isEmpty()){//첨부파일이 있다면
            //원본파일명
            String fname=mfileName.getOriginalFilename();
            long fsize=mfileName.getSize();//첨부파일 크기
            //물리적 파일명
            UUID uuid=UUID.randomUUID();
            String fileName=uuid.toString()+"_"+fname;
            log.info("fileName={}",fileName);

            if("edit".equals(board.getMode())){
                //새 첨부파일이 있을 경우 예전에 첨부했던 파일은 삭제
                if(board.getOld_fileName()!=null){
                    File tmp=new File(upDir,board.getOld_fileName());
                    if(tmp.exists()){
                        boolean b=tmp.delete();//삭제처리
                        log.info("예전 첨부파일 삭제 여부={}",b);
                    }
                }
            }

            board.setFileName(fileName);
            board.setOriginFilename(fname);
            board.setFileSize(fsize);
            //파일 업로드 처리
            try{
                mfileName.transferTo(new File(upDir, fileName));
                log.info("업로드 성공!!!");
            }catch(IOException ex){
                log.error("파일 업로드 실패: "+ex);
            }
        }//if------------

        //3. 글쓰기 or 글수정
        int n=0;
        if(board.getMode().equals("write")){
            log.info("방금 쓴 글의 글번호 1: {}", board.getNum());//0
            //for(int i=0;i<30;i++)
            n=boardService.insertBoard(board);//글쓰기 처리

            log.info("방금 쓴 글의 글번호 2: {}", board.getNum());//9
            msg=(n>0)?"글쓰기 성공":"글쓰기 실패";
            loc=(n>0)?"/board/list":"javascript:history.back()";

        }else if(board.getMode().equals("edit")){
            n=boardService.updateBoard(board);//수정 처리
            msg=(n>0)?"글수정 성공":"글수정 실패";
            loc=(n>0)?"/board/list":"javascript:history.back()";
        }
        return util.addMsgLoc(model,msg,loc);
    }//--------------------

    @GetMapping("/board/list_old") //페이징 처리 안할 경우
    public String boardList(Model model, PagingDTO paging){
        //1. 총 게시글 수 가져오기
        int totalCount=boardService.getTotalCount(paging);

        //2. 게시글 가져오기
        List<BoardDTO> list=boardService.listBoard(paging);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("boardList", list);
        return "board/boardList";
    }//----------------------

    @GetMapping("/board/list")
    public String boardList2(Model model, PagingDTO paging, HttpServletRequest req){
        log.info("paging===={}",paging);
        //1. 총 게시글 수 가져오기 or 검색한 게시글 수 가져오기
        int totalCount=boardService.getTotalCount(paging);
        paging.setTotalCount(totalCount);//총 게시글 수 설정
        paging.setOneRecordPage(5);//한 페이지에 보여줄 목록 개수
        /////////////////
        paging.init();//페이징 관련 연산을 수행하는 메서드 호출
        /////////////////

        //2. 게시글 가져오기
        List<BoardDTO> list=boardService.listBoard(paging);

        //3. 페이지 네비게이션 문자열 받아오기
        String myctx=req.getContextPath();//컨텍스트명
        log.info("myctx: {}",myctx);
        String loc="board/list";
        String pageNavi=paging.getPageNavi(myctx,loc);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("boardList", list);
        model.addAttribute("paging", paging);
        model.addAttribute("pageNavi", pageNavi);
        //return "board/boardList";//<c:forEach>로 페이지 처리
        return "board/boardList2"; //pageNavi문자열을 출력
    }//-----------------------------

    @GetMapping("/board/view")
    public String boardView(Model model,@RequestParam(defaultValue = "0") int num){
        log.info("num={}", num);
        if(num==0){
            return "redirect:/board/list";
        }
        int n =boardService.updateReadnum(num);//조회수 증가
        BoardDTO board =boardService.findBoardByNum(num);//게시글 가져오기
        model.addAttribute("board", board);
        return "board/boardView";
    }//-----------------

    @PostMapping("/user/delete")
    public String boardPasswdConfirm(
            Model model, HttpServletRequest req,
            @RequestParam(defaultValue = "0") int num,
            @RequestParam(defaultValue = "") String passwd){
        log.info("num={}, passwd={}",num,passwd);
        if(num==0||passwd.isBlank()){
            return "redirect:/board/list";
        }
        //해당 글을 DB에서 가져오기
       BoardDTO board=boardService.findBoardByNum(num);
        //비밀번호 체크
        if(!board.getPasswd().equals(passwd)){
            return util.addMsgBack(model,"비밀번호가 일치하지 않아요");
        }
        //db에서 글 삭제 처리
        int n=boardService.deleteBoardByNum(num);
        //서버에 업로드한 파일이 있다면 서버에서 삭제 처리
        ServletContext ctx=req.getServletContext();//application 객체
        String upDir=ctx.getRealPath("/board_upload");
        log.info("upDir={}",upDir);
        if(n>0 && board.getFileName()!=null){
            File file=new File(upDir, board.getFileName());
            if(file.exists()){
                boolean b=file.delete();//삭제 처리
                log.info("첨부파일 삭제여부={}",b);
            }//if---
        }//if---
        return "redirect:/board/list";
    }//------------------------

    @PostMapping("/user/edit")
    public String boardEditForm(Model model,
            @RequestParam(defaultValue = "0") int num,
            @RequestParam(defaultValue = "")  String passwd){
        if(num==0||passwd.isBlank()){
            return "redirct:/board/list";
        }
        BoardDTO board=boardService.findBoardByNum(num);
        if(board==null){
            return util.addMsgBack(model,"해당 글은 없습니다");
        }
        //비밀번호 체크
        if(! board.getPasswd().equals(passwd)){
            return util.addMsgBack(model,"비밀번호가 일치하지 않아요");
        }
        model.addAttribute("board",board);
        return "board/boardEdit";
    }//--------------------------------

}//class end/////////////////////////
