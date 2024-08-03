package com.study.board.controller;

import com.study.board.entitiy.Board;
import com.study.board.service.BoardService;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //해당 주소로 요청되었을 때 boardwrite 라는 html을 보여준다
    public String boardWriteForm() {
        return "boardwrite";
    } //따움표 안은 어떤 HTML 파일로 보내줄 것인지


    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {
        boardService.write(board, file);

        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.boardList(pageable); //service에서 받아온 board 데이터를 list라는 변수에 저장함

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage-4,1);
        int endPage = Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("list",list); //list라는 변수에 저장된 값을 boardlist.html로 보내줌
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardview(Model model, Integer id) {
        //localhost8080://board/view?id=1 로 하였을 때 id 값을 넘겨줌

        model.addAttribute("board",boardService.boardview(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}") //pathvariable 어도테이션을 써서 url의 id 값을 받아옴
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("board",boardService.boardview(id));
        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception{

        Board boardTemp = boardService.boardview(id); //받아온 id 값에 해당하는 기존 데이터(title, content) 에 접근할 수 있다.
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        model.addAttribute("message","글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }
}
