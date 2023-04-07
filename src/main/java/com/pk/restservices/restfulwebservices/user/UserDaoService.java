package com.pk.restservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	//UserDaoService > Static List
	 private static List<User> users = new ArrayList<>();
	 private static int usersCount =0;
	 
	 static {
		users.add(new User(++usersCount ,"Adam",LocalDate.now().minusYears(30)));
		 users.add(new User(++usersCount,"Eve",LocalDate.now().minusYears(25)));
		 users.add(new User(++usersCount,"Pavan",LocalDate.now().minusYears(20)));
	 }
	 
	 //return the list of users
	 public List<User> findAll()
	 {
		 return users;
	 }
	 
	 //find Specific user based on request
	 public User findOne(int id)
	 {
		 Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	 }
	 
	 //saving a new User to User class
	 public User saveUser(User user)
	 {
		user.setId(++usersCount);
		 users.add(user);
		 return user;
	 }
	 
	 //delete Specific user based on id
	 public void deleteById(int id)
	 {
		 Predicate<? super User> predicate = user -> user.getId().equals(id);
		 users.removeIf(predicate);
	}
}
