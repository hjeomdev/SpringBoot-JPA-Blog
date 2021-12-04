package com.yazbyz.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yazbyz.blog.model.Board;
import com.yazbyz.blog.model.User;
import com.yazbyz.blog.repository.BoardRepository;


@Service // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해 줌. IoC를 해준다.
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	public List<Board> 글목록() {
		return boardRepository.findAll();
	}
}
