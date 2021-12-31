package com.dat.parking.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalTime;
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
@Table(name = "parking_recordtbl",schema="public")
public class Parking {
	public int pid;
	public String carNo;
	public String floorName;
	public String slotName;
	public Timestamp entryTime;
	public Timestamp exitTime;
	public String duration;
	public Date date;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid")
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Column(name = "car_no", length = 20)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "floor_name", length = 20)
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	@Column(name = "slot_name", length = 20)
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	@Column(name = "entry_time")
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}
	@Column(name = "exit_time")
	public Date getExitTime() {
		return exitTime;
	}
	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
	}
	
	@Column(name = "duration")
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
