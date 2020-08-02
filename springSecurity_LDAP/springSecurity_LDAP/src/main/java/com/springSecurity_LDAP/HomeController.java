package com.springSecurity_LDAP;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String welcome(){
		
		return "<h1>Home Page<h1>";
		
	}
	

}
