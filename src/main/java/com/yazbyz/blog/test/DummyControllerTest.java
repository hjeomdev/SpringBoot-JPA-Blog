package com.yazbyz.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yazbyz.blog.model.RoleType;
import com.yazbyz.blog.model.User;
import com.yazbyz.blog.repository.UserRepository;

@RestController // html파일이 아니라 data를 리턴해주는 controller
public class DummyControllerTest {
	
	@Autowired // 의존성 주입 
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/user/1 (요청)
	// email, password 수정 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 -> Java Object (MessageConverter의 Jackson라이브러리가 변환해서 받아줘요.)
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());
		
		userRepository.save(user);
		return null;
	}
		
	//http://localhost:8000/blog/dummy/users (요청)
	@GetMapping("/dummy/users")
	public List<User> list() { 
		return userRepository.findAll();
	}
	
	// 한페이지 당 2건에 데이터를 리턴받아 볼 예정 
	//http://localhost:8000/blog/dummy/user (요청)
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id, direction = Sort.Direction.DESC") Pageable pageable) { 
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();
		return users;
	}
		
	//http://localhost:8000/blog/dummy/user/1 (요청)
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { 
//		User users = userRepository.findById(id).get(); // null 일 경우 에러 
//		User users = userRepository.findById(id).orElseGet(new Supplier<User>() { // 추천 안 함 
//			@Override
//			public User get() {
//				return new User(); // 빈 객체 반환 
//			}
//		});
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id); // 이 부분으로 에러페이지 사용 가능 
			}
		});
//		User user = userRepository.findById(id).orElseThrow(() -> { // 위 표현의 람다식 표현 (편리하다)
//			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//		});
		
		// 요청: 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동하여 객체를 json으로 변환하여 반환 
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)  
	@PostMapping("/dummy/join")
	public String join(
//			String username, String password, String email) { // x-www-form-urlencoded -> key=value (약속된 규칙)
			User user) { // 자동으로 객체로 만들어줌 
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER); //user.setRole("user");
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
