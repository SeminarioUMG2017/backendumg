package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.CardexTesoreria;
import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.UserPermission;

public interface UserRoleService extends JpaRepository<UserPermission, Serializable> {

	public final static String GET_ROLES = "  Select userrole FROM UserPermission userrole WHERE userrole.username.username = :user";


	
	
	@Query(GET_ROLES)   
	List<UserPermission> findRoles(@Param("user") String user);

	
}
