package com.dat.parking.dao;

import java.util.Date; 
import java.util.List;

import com.dat.parking.model.Admin;
import com.dat.parking.model.Floor;
import com.dat.parking.model.Parking;
import com.dat.parking.model.Slot;

public interface UserDAO {
	
	void persistParking(Parking parking);
	void updateParking(Parking parking);

	void addSlot(Slot slot);
	void addFloor(Floor floor);
	
	void removeSlot(String slotName,int fid);
	void removeFloor(int fid);
	
	void renameFloor(String oldName,String newName);
	void renameSlot(String oldName,String newName);
	
	void updateSlot(Slot slot);
	void changePassword(String password,String newpassword);
	
	Floor getFloorByName(String floorName);
	Slot getSlotByName(String slotName,int fid);
	Parking getParkingByCarNo(String carNo);
	
	public List checkOccupySlot(int fid);
    public List checkCarExist(String carNo);
	public List checkCarNo(String carNo,String floorName,String slotName);
	public List checkFloorExist(String floorName);
	public List checkSlotExist(String slotName,int fid);
	public List check(String userName, Date createdDate) ;
	public List login(String name,String password);
	
	public List<Floor> listFloors();
	public List<Slot> listSlots(int floorid);
	public List<Parking> listParkings(Date date);
	public List<String> carNoList();
	public Parking carInfo(String floorName, String slotName);
	public Parking searchCarNo(String carNo);
	public Long listOccupySlots(int floorid);
	public Long listAvailableSlots(int floorid);
	public Long listDisableSlots(int floorid);
}
