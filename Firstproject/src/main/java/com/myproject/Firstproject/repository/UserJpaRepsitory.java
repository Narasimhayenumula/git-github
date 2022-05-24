package com.myproject.Firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.Firstproject.DTOEntity.UserDTO;
@Repository
public interface UserJpaRepsitory extends JpaRepository<UserDTO, Long> {
		UserDTO findByName(String name);
		
}
