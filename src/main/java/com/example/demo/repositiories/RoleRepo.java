package com.example.demo.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{
	

}
