package com.dat.parking.model;

import java.util.Date; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.primefaces.model.file.UploadedFile;

@Entity
@Table(name = "admintbl",schema="public")
public class Admin {
	
	public String name;
	public String password;

	@Id
	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password",length =20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
