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
@Table(name = "floortbl",schema="public")
public class Floor {
	
	public int fid;
	public String floorName;	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fid")
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}
	
	@Column(name = "floor_name", length = 20)
	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
}
