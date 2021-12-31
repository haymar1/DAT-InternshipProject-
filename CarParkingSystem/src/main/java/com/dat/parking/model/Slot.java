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
@Table(name = "slottbl",schema="public")
public class Slot {
	public int sid;
	public String slotName;
	public int floorid;
	public String state;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sid")
	public int getId() {
		return sid;
	}
	public void setId(int sid) {
		this.sid = sid;
	}
	
	@Column(name = "slot_name",length =20)
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	@Column(name = "fid")
	public int getFloorid() {
		return floorid;
	}
	public void setFloorid(int floorid) {
		this.floorid = floorid;
	}
	@Column(name = "state",length =20)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


}
