package com.seminarioUMG.seminario.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.UserPermission;

public interface RoleService extends JpaRepository<Rol, Serializable> {
	
	public final static String GET_ROLES = "select rol.descripcion FROM rol;";
	public final static String FIND_ROL_BY_DESCRIPTION = "select * FROM rol where rol.descripcion = :role";

	
	
	
	
	@Query(value = GET_ROLES, nativeQuery = true)
	List<String> roles();
	
	
	@Query(value = FIND_ROL_BY_DESCRIPTION, nativeQuery = true)
	Rol findrolByDesc(@Param("role") String role);
	
}
