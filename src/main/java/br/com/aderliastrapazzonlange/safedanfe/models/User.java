package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
	private UserEnum permission;
	
	
	
	public User() {
	}

	public User(Long id, String name, String password, UserEnum permission) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.permission = permission;
	}
		
	public User(String name, String password, UserEnum permission) {
		this.name = name;
		this.password = password;
		this.permission = permission;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserEnum getPermission() {
		return permission;
	}

	public void setPermission(UserEnum permission) {
		this.permission = permission;
	}
	
	

}
