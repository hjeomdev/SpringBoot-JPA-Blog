package com.yazbyz.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yazbyz.blog.dto.ResponseDto;
import com.yazbyz.blog.model.RoleType;
import com.yazbyz.blog.model.User;
import com.yazbyz.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController: save 호출됨.");
		
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 json으로 변환해서 리턴 by Jackson
	}
	
//	전통적인 로그인 방식임... 사용안해! 
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("UserApiController: login 호출됨.");
//		User principal = userService.로그인(user); //principal == 접근주체 
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
//	}
	
	@PutMapping("/user/update")
	public ResponseDto<Integer> update(@RequestBody User user) {
		userService.회원수정(user); // 세션은 변경되지 않음
		
//		세션 등록 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		   
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
