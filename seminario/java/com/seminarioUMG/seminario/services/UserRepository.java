package com.seminarioUMG.seminario.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminarioUMG.seminario.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	User findOneByUsername(String username);
	
}
