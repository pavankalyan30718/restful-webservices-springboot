package com.pk.restservices.restfulwebservices.user;

import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;
	
	public UserResource(UserDaoService service)
	{
		this.service = service;
	}
	
	//we have to get details from UserDaoService
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return service.findAll();
	}
	
	//get Specific User
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveSpecificUser(@PathVariable int id)
	{
		User user = service.findOne(id);
		
		if(user == null)
		{
			throw new UserNotFoundException("id:" +id);
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		
		//Now Add link to retrieve all users using "WebMvcLinkBuilder"
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	//POST/users
	@PostMapping("/users")
	public void createUser(@Valid @RequestBody User user)
	{
		service.saveUser(user);
	} 
	
	//DELETE/users/{path-variable}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		service.deleteById(id);
	}
}
