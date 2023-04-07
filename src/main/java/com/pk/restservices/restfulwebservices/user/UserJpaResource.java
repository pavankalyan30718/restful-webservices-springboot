package com.pk.restservices.restfulwebservices.user;

import java.net.URI;
//used after learning JPA Mapping
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pk.restservices.restfulwebservices.jpa.PostRepository;
import com.pk.restservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserDaoService service;
	
	private UserRepository repository;
	private PostRepository postrepository;
	
	public UserJpaResource(UserDaoService service, UserRepository repository, PostRepository postrepository)
	{
		this.service = service;
		this.repository = repository;
		this.postrepository= postrepository;
	}
	
	//we have to get details from UserDaoService
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		//return service.findAll();
		return repository.findAll();
	}
	
	//get Specific User
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveSpecificUser(@PathVariable int id)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:" +id);
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		//Now Add link to retrieve all users using "WebMvcLinkBuilder"
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	//POST/users
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User savedUser =service.saveUser(user);
	     
		//adding URI Location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	} 
	
	//DELETE/users/{path-variable}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		//service.deleteById(id);
		repository.deleteById(1002);
	}
	
	//Getmapping with posts
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:"+id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:"+id);
		}
		
		post.setUser(user.get());
		postrepository.save(post);
		//adding URI Location
		Post savedPost = postrepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
								.path("/{id}")
								.buildAndExpand(savedPost.getId())
								.toUri();
				
		return ResponseEntity.created(location).build();
		//return user.get().getPosts();
	}
}
