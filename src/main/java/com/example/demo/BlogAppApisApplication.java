package com.example.demo;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.demo.config.AppConstants;
import com.example.demo.entities.Role;
import com.example.demo.repositiories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

@Autowired
private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
return new ModelMapper();
}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
		List<Role> roles =	List.of(role,role1);
		List<Role> result = this.roleRepo.saveAll(roles);
		result.forEach(r->{
			System.out.println(r.getName());
		});
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	}
}