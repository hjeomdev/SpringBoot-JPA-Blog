package com.yazbyz.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert // insert 시에 null 인 필드 제외시켜
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // oracle -> 시퀀스, mysql -> auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. 
	
	@CreationTimestamp // save 할 때 자바에서 현재 시간을 넣어줌 
	private Timestamp createDate;
}
