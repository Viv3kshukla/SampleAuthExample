package com.auth.application.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.application.model.User;
import com.auth.application.repository.UserRepository;

@RestController
@RequestMapping("/secure")
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public String addUserByAdmin(@RequestBody User user) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			System.out.println("\n\n\n Hi users "+username+"\n\n\n");
		}  
		else {
			String username = principal.toString();
			System.out.println("\n\n\n Hi users "+username+"\n\n\n");
			
		}
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		System.out.println("\n\n\n the role of the user is  "+ authorities +" ");
		
		
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		userRepository.save(user);
		return "user added successfully...";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/all")
	public String securedHello() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			System.out.println("\n\n\n Hi users "+username+"\n\n\n");
		}  
		else {
			String username = principal.toString();
			System.out.println("\n\n\n Hi users "+username+"\n\n\n");
			
		}
		
		return "Secured Hello";
	}
	
	
	@GetMapping("/any")
	public String sayHello() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			System.out.println("\n\n\n Hi users "+username+"\n\n\n");
		}  
		else {
			String username = principal.toString();
			System.out.println("\n\n\n Hi users "+username+"\n\n\n");
			
		}
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		System.out.println("\n\n\n the role of the user is  "+ authorities +" ");
		return "Say Hello";
	}
	
}
