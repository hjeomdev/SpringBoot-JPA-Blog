package com.yazbyz.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yazbyz.blog.model.User;

// DAO
// 자동으로 bean등록된다. 
//@Repository // 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}
