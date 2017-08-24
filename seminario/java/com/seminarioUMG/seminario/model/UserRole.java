package com.seminarioUMG.seminario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
	@Table(name = "roles")
	@NamedQueries({
		@NamedQuery(
			name  = "user.findRolesByUserName",
			query =
					"SELECT ur              " +
					"  FROM UserRole ur     " +
					" WHERE ur.user = :user "
		)
	})
	public class UserRole {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private @Column(name = "id_authorities"             ) Integer userRoleId;
		private @Column(name = "username"    , length = 45) String  user;
		private @Column(name = "authority"   , length = 45) String  role;
		
		public Integer getUserRoleId() {
			return userRoleId;
		}
		public void setUserRoleId(Integer userRoleId) {
			this.userRoleId = userRoleId;
		}
		
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
			

	

}
