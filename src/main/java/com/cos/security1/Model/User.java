package com.cos.security1.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String username;
	private String password;
	private String eamil;
	private String role;
	private String provider; //"google"
	private String provider_id;  // 구글에서 사용 하는 sub 값
	
	@CreationTimestamp
	private Timestamp createDate;


	
	
}
