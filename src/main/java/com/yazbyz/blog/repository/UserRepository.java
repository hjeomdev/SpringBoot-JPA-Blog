package com.yazbyz.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yazbyz.blog.model.User;

// DAO
// 자동으로 bean등록된다. 
//@Repository // 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// SELECT * fROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);

}
//전통적인 로그인 방식임... 사용안해! 
//User findByUsernameAndPassword(String username, String password);
//@Query(value="SELETE * FROM user WHERE username = ?1 AND password = ?2;", nativeQuery=true)
//User login(String username, String password);