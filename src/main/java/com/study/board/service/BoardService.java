package com.study.board.service;

import com.study.board.entitiy.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);
        board.setFilepath("/files/"+fileName);

        board.setFilename(fileName);

        boardRepository.save(board);
        //controller에서 보내준 board 데이터를 받아와서
        // autowired 된 boardRepository의 jpaRepository 메소드를 사용하여 save한다.
    }

    // 게시판 리스트 처리
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
        //controller에서 요청한 board 데이터를 보내줌
    }

    // 특정 게시글 불러오기
    public Board boardview(Integer id) {

        return boardRepository.findById(id).get();
        //JPA의 메소드인 findById 는 optional 객체로 .get() 함수를 써야지 실체값을 받아올 수 있다.
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        //리턴값이 필요없기 때문에 void

        boardRepository.deleteById(id);
    }
}
