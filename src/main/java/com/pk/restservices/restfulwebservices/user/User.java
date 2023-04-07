package com.pk.restservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {
	
	protected User()
	{
		
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message = "Name should have atleast 2 characters") //minimum 2 characters validation
	@Column(name="name")
	private String name;
	
	@Past(message = "Birth Date should be in the past")//birth date should be in past validation
	@Column(name="birthdate")
	private LocalDate brithdate;
	
	//One to Many relationships (posts concept)
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	public User(Integer id, String name, LocalDate brithdate) {
		super();
		this.id = id;
		this.name = name;
		this.brithdate = brithdate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBrithdate() {
		return brithdate;
	}
	public void setBrithdate(LocalDate brithdate) {
		this.brithdate = brithdate;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", brithdate=" + brithdate + "]";
	}
}
