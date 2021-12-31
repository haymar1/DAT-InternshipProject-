package com.dat;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.primefaces.model.file.UploadedFile;

import com.dat.parking.model.Admin;
import com.dat.parking.model.Floor;
import com.dat.parking.model.Parking;
import com.dat.parking.model.Slot;
import com.dat.parking.service.UserService;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
@RolesAllowed("admin")
public class UserBean implements Serializable {

	@ManagedProperty(value = "#{userService}")
	UserService userService;
	//parking record
	public int pid;
	public String carNo;
	public String FloorName;
	public String SlotName;
	public Timestamp entryTime;
	public Timestamp exitTime;
	public String duration;
	public Date date;
	public Parking parkingCtl=new Parking();
	
	// admin
	public String name;
	public String password;
	public String newpassword;
	public Admin adminCtl = new Admin();

	// floor
	public int fid;
	public String floorName;
	public String newFloorName;
	public Floor floorCtl = new Floor();

	// slot
	public int sid;
	public String slotName;
	public int floorid;
	public String state;
	public String  newSlotName;
	public Slot slotCtl = new Slot();
	
	Date tempdate;
	public List<Floor> floors = new ArrayList<Floor>();
	public List<Slot> slots = new ArrayList<Slot>();
	public List<String> cars=new ArrayList<String>();
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getNewFloorName() {
		return newFloorName;
	}

	public void setNewFloorName(String newFloorName) {
		this.newFloorName = newFloorName;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public int getFloorid() {
		return floorid;
	}

	public void setFloorid(int floorid) {
		this.floorid = floorid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
	public List<String> getCars() {
		return cars;
	}

	public void setCars(List<String> cars) {
		this.cars = cars;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin getAdminCtl() {
		return adminCtl;
	}

	public void setAdminCtl(Admin adminCtl) {
		this.adminCtl = adminCtl;
	}

	public Floor getFloorCtl() {
		return floorCtl;
	}

	public void setFloorCtl(Floor floorCtl) {
		this.floorCtl = floorCtl;
	}

	public Slot getSlotCtl() {
		return slotCtl;
	}

	public void setSlotCtl(Slot slotCtl) {
		this.slotCtl = slotCtl;
	}
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getNewSlotName() {
		return newSlotName;
	}

	public void setNewSlotName(String newSlotName) {
		this.newSlotName = newSlotName;
	}
	public String adminParking() {
		parkingCtl.setCarNo(null);
		return "adminParking";
	}
	public String backAdminParking() {
		parkingCtl.setCarNo(null);
		return "adminParking";
	}
	
	public String manageSlots() {
		slotCtl.setSlotName(null);
		return "manageSlots";
	}
	public String manageParking() {
		floorCtl.setFloorName(null);
		slotCtl.setSlotName(null);
		return "manageParking";
	}

	public String validate() throws IOException{
		List t = userService.login( adminCtl.getName(), adminCtl.getPassword());
		
		if (t.isEmpty()) {		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Invalid log in"));
			
			return "home";
		} else {
			parkingCtl.setCarNo(null);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Log in successful"));
			
			return "adminParking";
		}
	}
	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().invalidateSession();
    	context.addMessage(null, new FacesMessage("Log out successful"));
    	
        try {
			context.getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String changePassword() {
		
		userService.changePassword(this.adminCtl.getPassword() , getNewpassword());
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Password changed successfully"));
		return "changePassword";
	}

	// method CRUD
	public String addFloor() {
		List t=userService.checkFloorExist(floorCtl.getFloorName());
		if(t.isEmpty()) {	
		userService.addFloor(floorCtl);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Floor added successfully"));
		floorCtl.setFloorName(null);
		return "manageParking";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","This floor name is already exist.Try another name"));
			
			return "manageParking";
		}
	}

	public String addSlot(int fid) {
		List t=userService.checkSlotExist(slotCtl.getSlotName(),fid);
		if(t.isEmpty()) {
		slotCtl.setFloorid(floorCtl.getFid());
		slotCtl.setState("available");
		userService.addSlot(slotCtl);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Slot added successfully"));
		slotCtl.setSlotName(null);
		return "manageSlots";
		}else 
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","This slot name is already exist. Try another name"));
			return "manageSlots";
		}
	}

	public String removeFloor() {
		Floor floor = userService.getFloorByName(getFloorName());
		List t= userService.checkOccupySlot(floor.getFid());
		if(t.isEmpty()) {
		userService.removeFloor(floor.getFid());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Floor removed successfully"));
		return "manageParking";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Removed failed.This floor has occupied slots"));
			setFloorName(null);
			return "manageParking";
		}
	}

	public String removeSlot(int fid) {
		Slot slot=userService.getSlotByName(this.slotCtl.getSlotName(),fid);
		if(slot.getState().equals("occupy")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Remove failed.This slot is occupied by a car."));		
			slotCtl.setSlotName(null);
			return "manageSlots";
		}else {
		userService.removeSlot(slotCtl.getSlotName(), floorCtl.getFid());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Slot removed successfully"));		
		return "manageSlots";
		}
	}
	public String renameFloor() {
		Floor floor = userService.getFloorByName(getFloorName());
		List t1= userService.checkOccupySlot(floor.getFid());;
		if(t1.isEmpty()) {
		List t=userService.checkFloorExist(getNewFloorName());
		if(t.isEmpty()) {
		userService.renameFloor(getFloorName(),getNewFloorName());	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Floor renamed successfully"));
		setNewFloorName(null);
		floorCtl.setFloorName(null);
		return "manageParking";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","This floor name is already exsit.Try another name."));
			setNewFloorName(null);
			setFloorName(null);
			return "manageParking";
		}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Rename failed.This floor has occupied slots"));
			setNewFloorName(null);
			setFloorName(null);
			return "manageParking";
		}
		
	}
	public String renameSlot(int fid) {
		Slot slot=userService.getSlotByName(this.slotCtl.getSlotName(), fid);
		if(slot.getState().equals("occupy")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Rename failed.This slot is occupied by a car"));
			setNewSlotName(null);
			slotCtl.setSlotName(null);
			return "manageSlots";
		}else {
		List t=userService.checkSlotExist(getNewSlotName(),floorCtl.getFid());
		if(t.isEmpty()) {
		userService.renameSlot(slotCtl.getSlotName(),getNewSlotName());	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Slot renamed successfully"));
		setNewSlotName(null);
		slotCtl.setSlotName(null);
		return "manageSlots";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","This slot name is already exsit.Try another name."));
			setNewSlotName(null);
			slotCtl.setSlotName(null);
			return "manageSlots";
		}
	  }
		
	}
	public String editSlot(Slot slotCtl) {
		this.slotCtl=slotCtl;
		if(slotCtl.getState().equals("occupy")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Info","Can't edit because this slot is occupied by a car"));
			slotCtl.setSlotName(null);
			return "manageSlots";
		}
		return "editSlots";
	}
	public String edituserSlot(Slot slot1) {
		this.slotCtl=slot1;
		if(slot1.getState().equals("available")) {
			this.parkingCtl.setCarNo(getCarNo());
		return "occupySlot";
		}else if(slot1.getState().equals("occupy")) {
			this.parkingCtl=userService.carInfo(floorCtl.getFloorName(),slotCtl.getSlotName());
			return "availableSlot";	
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","This slot is temporarily unavailable"));
			return "userSlots";
		}
	}
	public String updateSlot() {
		
		getUserService().updateSlot(slotCtl);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Slot updated successfully"));
		slotCtl.setSlotName(null);
		return "manageSlots";

	}
	public String occupySlot() {
		List t=userService.checkCarExist(parkingCtl.getCarNo());
		if(t.isEmpty()) {
		parkingCtl.setEntryTime(new Timestamp(System.currentTimeMillis()));
		parkingCtl.setDate(new Date());
		parkingCtl.setFloorName(floorCtl.getFloorName());
		parkingCtl.setSlotName(slotCtl.getSlotName());
		slotCtl.setState("occupy");
		userService.persistParking(parkingCtl);
		userService.updateSlot(this.slotCtl);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Occupied successfully"));
		parkingCtl.setCarNo(getCarNo());
		return "userSlots";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Info","This CarNo is already parked."));
			return "userSlots";
		}
	}
	public String searchCarNo() {
		if(userService.searchCarNo(parkingCtl.carNo) != null) {
			this.parkingCtl=userService.searchCarNo(parkingCtl.carNo);
		    return "searchCarNo";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Info","This car is not in the parking list."));
			parkingCtl.setCarNo(null);
			return "adminParking";
		}
	}
	public String availableSlot() {
		List t=userService.checkCarNo(parkingCtl.getCarNo(), floorCtl.getFloorName(),slotCtl.getSlotName());
		if(t.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Invalid car no . Please try again"));
			return "userSlots";
		}else {
			Parking parking=userService.getParkingByCarNo(parkingCtl.getCarNo());
			parking.setExitTime(new Timestamp(System.currentTimeMillis()));		
			slotCtl.setState("available");
			userService.updateParking(parking);
			parking=userService.getParkingByCarNo(parkingCtl.getCarNo());
			parking.setDuration(findDuration(parking.getEntryTime(),parking.getExitTime()));
			userService.updateParking(parking);
			userService.updateSlot(this.slotCtl);
			parkingCtl.setCarNo(getCarNo());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Available successfully"));
		return "userSlots";
		}
	}
	public String timer(Date entryTime) throws ParseException {
		Date temptime=new Date();
		long seconds=TimeUnit.MILLISECONDS.toSeconds(temptime.getTime()-entryTime.getTime());
		long s=seconds%60;
		long m=seconds/60;
		long h=m/60;
		m=m%60;
		String duration=h+":"+m+":"+s;
		SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
		Date date=sdf.parse(duration);
		duration=sdf.format(date);
		return duration;
	}
	public String findDuration(Date entryTime,Date exitTime) {
		long seconds=TimeUnit.MILLISECONDS.toSeconds(exitTime.getTime()-entryTime.getTime());
		long s=seconds%60;
		long m=seconds/60;
		long h=m/60;
		m=m%60;
		String duration=h+":"+m+":"+s;
		SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
		Date date=sdf.parse(duration);
		duration=sdf.format(date);
		return duration;
	}
	

	public List<Slot> listSlots(int floorid) {

		return this.userService.listSlots(floorid);
	}

	public List<Floor> listFloors() {
		return this.userService.listFloors();
	}
	public String searchByDate() {
		return "parkingReport";
	}
	
	public List<Parking> listParkings(){
		
	return this.userService.listParkings(tempdate);
	}

	public String editFloor(Floor floorCtl) {
		this.floorCtl = floorCtl;
		return "manageSlots";
	}
	public String edituserFloor(Floor floorCtl) {
		this.floorCtl = floorCtl;
		return "userSlots";
	}
	public String color(String state) {
		if(state.equals("occupy")) {
			return "red";
		}else if(state.equals("disable")) {
			return "grey";
		}else {
		return "green";
		}
	}
	public String disableColor(String state) {
		if(state.equals("disable")) {
			return "grey";
		}else return "white";
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Parking getParkingCtl() {
		return parkingCtl;
	}

	public void setParkingCtl(Parking parkingCtl) {
		this.parkingCtl = parkingCtl;
	}

	public Date getTempdate() {
		return tempdate;
	}

	public void setTempdate(Date tempdate) {
		this.tempdate = tempdate;
	}
	//hsu myat htet
	public String viewadminFloor(Floor floorCtl) {
		this.floorCtl = floorCtl;
		return "adminSlots";
	}
	public String showinfoSlot(Slot slot) {
		
		if(slot.getState().equals("occupy")) {

		this.parkingCtl = userService.carInfo(floorCtl.getFloorName(),slot.getSlotName());
		return "showinfoSlots";
		
		}else if(slot.getState().equals("available")) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","No car is parked in this slot."));
			return "adminSlots";
			
		}else {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","This slot is temporarily unavailable"));
			return "adminSlots";
		}

	}
	
	public List<String> listCars(String enteredValue){
		List<String> matches = new ArrayList<>();
		for(String s: userService.carNoList()) {
			if(s.startsWith(enteredValue)) {
				matches.add(s);
			}
		}
		return matches;
	}
	/**public List<String> listCars(String enteredValue){
		List<String> results = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            results.add(enteredValue + i);
        }
         
        return results;
	}**/
	//hmh
		public Long listOccupySlots(int floorid) {

			return this.userService.listOccupySlots(floorid);
		}
		public Long listAvailableSlots(int floorid) {

			return this.userService.listAvailableSlots(floorid);
		}
		public Long listDisableSlots(int floorid) {

			return this.userService.listDisableSlots(floorid);
		}
	
	
}

