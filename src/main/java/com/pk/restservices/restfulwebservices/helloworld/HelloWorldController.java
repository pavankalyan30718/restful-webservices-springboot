package com.pk.restservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//This Controller would expose REST API
//First Step Mark this controller as Rest Controller
//Annotation which makes a specific controller , Rest Controller
@RestController 
public class HelloWorldController {
	
	//getting Message Source to access messages.properties
	private MessageSource messagesource; 
	
	public HelloWorldController(MessageSource messagesource)
	{
		this.messagesource = messagesource;
	}
	//We have to map this request to a Get Request Method
	@GetMapping(path = "/hello-world")
	public String helloWorld()
	{
		return "Hello World!";
	}
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World Bean!");
	}
	
	//Internationalized -- i18n
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized()
	{
		Locale locale = LocaleContextHolder.getLocale();
		return messagesource.getMessage("good.morning.message", null, "Default Message", locale);
		//return new HelloWorldBean("Hello World Bean!");
	}
	
//path variable & urls
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello World!, %s", name));
	}
}
