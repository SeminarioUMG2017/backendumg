package com.seminarioUMG.seminario.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



@Entity
@Table(name = "users")
@NamedQueries({
	@NamedQuery(
		name  = "user.findAllUsers",
		query =
				"SELECT u        " +
				"FROM User u     "
	)
})
public class User {
	private static final Log logger = LogFactory.getLog(User.class);
	@Id
	private @Column(name = "username", length = 45                 ) String  user;
	private @Column(name = "password", length = 60, nullable = true) String  password;
	private @Column(name = "enabled" ,              nullable = true) Boolean enabled;
	
	public User(String user, String password, Boolean enabled, boolean b,
			boolean c, boolean d, List<Object> authorities) {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public User() {

	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	 public int getColumnCount() {
	        return getClass().getDeclaredFields().length;
	    }
	
}
