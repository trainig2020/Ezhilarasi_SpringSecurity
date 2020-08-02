package com.springSecurityUsingJPA_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springSecurityUsingJPA_1.dao.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringSecurityUsingJpa1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityUsingJpa1Application.class, args);
	}

}
