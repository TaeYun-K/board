package com.study.board.repository;

import com.study.board.entitiy.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> { //entity, 기본키의 type
}

//entity 클래스를 정의하고, jpaRepository 인터페이스를 상속받는다.
//JpaRepository 인터페이스에 정의된 메서드를 사용할 수 있다. ex) boardRepository.getUserByID()