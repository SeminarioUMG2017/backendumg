package com.seminarioUMG.seminario.services;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	public final static String GET_ROLES_BY_USER = "select  rol.codigo_rol, rol.descripcion from users us inner join userroles uroles on us.username = uroles.username inner join rol rol on uroles.roles = rol.codigo_rol where us.username = :username";

	public final static String GET_FIRST_LOGIN = "select us.confirm from users where us.username = :username";

	
	User findOneByUsername(String username);
	
	@Query(value = GET_ROLES_BY_USER , nativeQuery = true)
	
	List<Rol> listRoles(@Param("username") String username);
	
	@Query(value = GET_FIRST_LOGIN , nativeQuery = true)
	Integer confirm(@Param("username") String username);	

	
}
