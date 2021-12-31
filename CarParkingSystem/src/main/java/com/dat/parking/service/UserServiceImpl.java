package com.dat.parking.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dat.parking.dao.UserDAO;
import com.dat.parking.model.Admin;
import com.dat.parking.model.Floor;
import com.dat.parking.model.Parking;
import com.dat.parking.model.Slot;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired 
	UserDAO userDAO;
	
	@Override
	public void addSlot(Slot slot) {
		userDAO.addSlot(slot);
	}
	
	@Override
	public void addFloor(Floor floor) {
		userDAO.addFloor(floor);
	}
	
	@Override
	public void removeSlot(String slotName,int fid) {
		userDAO.removeSlot(slotName,fid);
	}
	
	@Override
	public void removeFloor(int fid) {
		userDAO.removeFloor(fid);
	}
	
	@Transactional
	@Override
	public Floor getFloorByName(String floorName) {
		return userDAO.getFloorByName(floorName);
	}
	
	@Transactional
	@Override
	public Slot getSlotByName(String slotName,int fid) {
		return userDAO.getSlotByName(slotName,fid);
	}
	
	@Transactional
	@Override
	public Parking getParkingByCarNo(String carNo) {
		return userDAO.getParkingByCarNo(carNo);
	}
	
	@Transactional
	@Override
	public void renameFloor(String oldName,String newName) {
		 userDAO.renameFloor(oldName,newName);
	}
	
	@Transactional
	@Override
	public void renameSlot(String oldName,String newName) {
		userDAO.renameSlot(oldName, newName);
	}
	
	@Transactional
	@Override
	public void changePassword(String password,String newpassword) {
		userDAO.changePassword(password, newpassword);
	}
	
	@Transactional
	@Override
	public void updateSlot(Slot slot) {
		userDAO.updateSlot(slot);
	}

	@Transactional
	@Override
	public void persistParking(Parking parking) {
		userDAO.persistParking(parking);
	}
	
	@Transactional
	@Override
	public void updateParking(Parking parking) {
		userDAO.updateParking(parking);
	}
	
	@Transactional
	@Override
	public List checkFloorExist(String floorName) {
		return this.userDAO.checkFloorExist(floorName);
	}
	
	@Transactional
	@Override
	public List checkSlotExist(String slotName,int fid) {
		return this.userDAO.checkSlotExist(slotName , fid);
	}

	@Transactional
	@Override
	public List<Floor> listFloors(){
		return this.userDAO.listFloors();
	}
	
	@Transactional
	@Override
	public List<Slot> listSlots(int floorid){
		return this.userDAO.listSlots(floorid);
	}
	
	@Transactional
	@Override
	public List<Parking> listParkings(Date date){
		return this.userDAO.listParkings(date);
	}
	
	@Transactional
	@Override
	public List checkCarExist(String carNo) {
		return this.userDAO.checkCarExist(carNo);
	}
	
	@Transactional
	@Override
	public Parking searchCarNo(String carNo) {
		return this.userDAO.searchCarNo(carNo);
	}
	
	@Transactional
	@Override
	public List checkCarNo(String carNo,String floorName,String slotName) {
		return this.userDAO.checkCarNo(carNo, floorName, slotName);
	}
	
	public List login(String name,String password) {
		
		return this.userDAO.login(name, password);
	}
	
	@Transactional
	@Override
	public Parking carInfo(String floorName,String slotName) {
		return this.userDAO.carInfo( floorName, slotName);
	}
	
	@Transactional
	@Override
	public List checkOccupySlot(int fid) {
		return this.userDAO.checkOccupySlot(fid);
	}
	
	@Transactional
	@Override
	public List<String> carNoList(){
		return this.userDAO.carNoList();
	}

	//hmh
	@Transactional
	@Override
	public Long listOccupySlots(int floorid) {
		return this.userDAO.listOccupySlots(floorid);
	}
	@Transactional
	@Override
	public Long listAvailableSlots(int floorid) {
		return this.userDAO.listAvailableSlots(floorid);
	}
	@Transactional
	@Override
	public Long listDisableSlots(int floorid) {
		return this.userDAO.listDisableSlots(floorid);
	}
}

