package com.yazbyz.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazbyz.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}