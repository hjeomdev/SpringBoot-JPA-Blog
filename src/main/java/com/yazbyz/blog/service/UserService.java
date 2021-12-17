package com.yazbyz.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yazbyz.blog.model.RoleType;
import com.yazbyz.blog.model.User;
import com.yazbyz.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해 줌. IoC를 해준다.
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

//	전통적인 로그인 방식임... 사용안해! 
//	@Transactional(readOnly = true) // Select할때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	@Transactional
	public void 회원수정(User user) {
//		수정 시에는 영속성 컨텍스트에 수정할 오브젝트를 영속화하고, 영속화된 오브젝트를 수정해야한다.
//		영속화하기 위해서, select해서 오브젝트를 DB로부터 가져와야한다. 
//		영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날리기 때문.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		String encPassword = encoder.encode(user.getPassword());
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
//		이 함수가 종료 == 서비스 종료 == 트랜잭션 종료 == commit 자동 실행
//		영속화된 오브젝트의 변화가 감지되면 더티체킹되어 update문 실행. 
	}
}
