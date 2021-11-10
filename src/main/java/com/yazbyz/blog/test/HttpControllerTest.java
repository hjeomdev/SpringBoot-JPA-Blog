package com.yazbyz.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller //  사용자의 요청에 HTML파일로 응답하는 컨트롤러 
@RestController // 사용자의 요청에 데이터로 응답하는 컨트롤러 
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@naver.com").build();
		System.out.println(TAG + "getter: " + m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "setter: " + m.getUsername());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get요청 밖에 할 수 없다.
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getText(Member m) { // 쿼리 스트링 한번에 받기(자동으로 매핑되어 객체에 들어온다) 
			//@RequestParam int id, @RequestParam String username) { // 쿼리 스트링 하나하나 받기
		return "get 요청" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postText
			//(@RequestBody String text) { // text/plain
			(@RequestBody Member m) {// application/json // 스프링부트에 MessageConverter가 자동 매핑 시켜줌.
		return "post 요청:" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putText(@RequestBody Member m) {
		return "put 요청" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteText(@RequestBody Member m) {
		return "delete 요청" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
}
