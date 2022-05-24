package com.myproject.Firstproject.DTOEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="users")
public class UserDTO {
		@Id
		@GeneratedValue
		@Column(name="USER_ID")
		private Long id;
		@Column(name="NAME")
		private String name;
		//@Column(name="enabled")
		//private boolean isEnable;
		//@Column(name="role")
		//private String role;
		@Column(name="ADRESS")
		private String adress;
		//public boolean isEnable() {
		//	return isEnable;
		//}
		//public void setEnable(boolean isEnable) {
		//	this.isEnable = isEnable;
		//}
		//public String getRole() {
		//	return role;
		//}
		//public void setRole(String role) {
		//this.role = role;
		//}
		@Column(name="EMAIL")
		private String email;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		
}
