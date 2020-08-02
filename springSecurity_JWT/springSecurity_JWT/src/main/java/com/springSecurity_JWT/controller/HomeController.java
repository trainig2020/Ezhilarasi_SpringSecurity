package com.springSecurity_JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurity_JWT.model.AuthenticationRequest;
import com.springSecurity_JWT.model.AuthenticationResponse;
import com.springSecurity_JWT.service.MyUserDetailsService;
import com.springSecurity_JWT.util.JWTUtil;

@RestController
public class HomeController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JWTUtil jWTUtil;
	
	@GetMapping("/")
	public String welcome(){
		
		return "<h1>Home Page<h1>";
		
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e){
			throw new Exception("Username,Password incorrect",e);
		}
		
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jWTUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	

}
