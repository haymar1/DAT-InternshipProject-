package com.dat.parking.dao;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dat.parking.model.Admin;
import com.dat.parking.model.Floor;
import com.dat.parking.model.Parking;
import com.dat.parking.model.Slot;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{

	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public void persistParking(Parking parking) {
		sessionFactory.getCurrentSession().save(parking);
	}
	@Override
	public void updateParking(Parking parking) {
		sessionFactory.getCurrentSession().update(parking);
	}
	
	@Override
	public void addSlot(Slot slot) {
		sessionFactory.getCurrentSession().save(slot);
	}
	
	@Override
	public void addFloor(Floor floor) {
		sessionFactory.getCurrentSession().save(floor);
	}
	
	@Override
	public void removeSlot(String slotName,int fid) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="delete from Slot where slotName=:slotName AND fid=:floorid";
		Query query=session.createQuery(s);
		query.setParameter("slotName", slotName).setParameter("floorid", fid);
		query.executeUpdate();
		
		
	}
	
	@Override
	public void removeFloor(int fid) {
		Session session=this.sessionFactory.getCurrentSession();
		String s1="delete from Slot where fid=:floorid";
		Query query1=session.createQuery(s1);
		query1.setParameter("floorid", fid);
		query1.setParameter("floorid", fid);
		query1.executeUpdate();
		String s2="delete from Floor where fid=:fid";
		Query query2=session.createQuery(s2);
		query2.setParameter("fid",fid);
		query2.executeUpdate();
	}
	
	@Override
	public Floor getFloorByName(String floorName) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Floor where floorName=:floorName order by fid desc";
		Query query=session.createQuery(s);
		query.setParameter("floorName", floorName);
		query.setMaxResults(1);
		return (Floor)query.uniqueResult();
	}
	
	@Override
	public Slot getSlotByName(String slotName,int fid) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Slot where slotName=:slotName AND fid=:fid order by sid desc";
		Query query=session.createQuery(s);
		query.setParameter("slotName", slotName);
		query.setParameter("fid", fid);
		query.setMaxResults(1);
		return (Slot)query.uniqueResult();
	}
	
	@Override
	public Parking getParkingByCarNo(String carNo) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Parking where carNo=:carNo order by pid desc";
		Query query=session.createQuery(s);
		query.setParameter("carNo",carNo);
		query.setMaxResults(1);
		return (Parking)query.uniqueResult();
	}
	@Override
	public void renameFloor(String oldName,String newName) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="update Floor set floorName=:newName where floorName=:oldName";
		Query query=session.createQuery(s);
		query.setParameter("newName", newName).setParameter("oldName", oldName);
		query.executeUpdate();
	}
	@Override
	public void renameSlot(String oldName,String newName) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="update Slot set slotName=:newName where slotName=:oldName";
		Query query=session.createQuery(s);
		query.setParameter("newName", newName).setParameter("oldName", oldName);
		query.executeUpdate();
	}
	
	@Override
	public void changePassword(String password,String newpassword) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="update Admin set password=:newpassword where password=:password";
		Query query=session.createQuery(s);
		query.setParameter("newpassword", newpassword).setParameter("password", password);
		query.executeUpdate();
	}
	
	@Override
	public void updateSlot(Slot slot) {
		sessionFactory.getCurrentSession().update(slot);		
	}
	

	@Override
	public List<Floor> listFloors(){
		Session session=this.sessionFactory.getCurrentSession();
		List<Floor> floorList=session.createQuery("from Floor order by fid asc").list();
		for(Floor f : floorList) {
			
		}
		return floorList;
	}
	@Override
	public List<String> carNoList(){
		Session session=this.sessionFactory.getCurrentSession();
		List<String> carNoList=session.createQuery("select carNo from Parking where exitTime=null").list();
		for(String s: carNoList) {
			
		}
		return carNoList;
	}
	
	
	@Override
	public List<Slot> listSlots(int floorid){
		
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Slot \r\n"+
				 "where fid=:floorid order by sid asc";
		Query query=session.createQuery(s);
		query.setParameter("floorid",floorid);
		List<Slot> slotList=query.list();
		for(Slot s1 : slotList){
			
		}
		
		return slotList;
	}
	
	@Override
	public List<Parking> listParkings(Date date){
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Parking \r\n"+
				 "where date=:date AND duration!=NULL order by pid asc";
		Query query=session.createQuery(s);
		query.setParameter("date", date);
		List<Parking> parkingList=query.list();
		for(Parking p: parkingList) {
			
		}
		return parkingList;
	}
	@Override 
	public List checkFloorExist(String floorName) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="select floorName from Floor where floorName=:floorName";
		Query query=session.createQuery(s);
		query.setParameter("floorName", floorName);
		List result=query.list();
		return result;
	}
	@Override
	public List checkSlotExist(String slotName,int fid) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Slot where slotName=:slotName AND fid=:fid";
		Query query=session.createQuery(s);
		query.setParameter("slotName", slotName).setParameter("fid", fid);
		List result=query.list();
		return result;
	}
	
	@Override
	public List checkCarNo(String carNo,String floorName,String slotName) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="select carNo from Parking \r\n"+
		          "where carNo=:carNo AND floorName=:floorName AND slotName=:slotName";
		Query query=session.createQuery(s);
		query.setParameter("carNo", carNo);
		query.setParameter("floorName", floorName);
		query.setParameter("slotName", slotName);
		List result=query.list();
		return result;
	}
	
	
	@Override
	public List check(String userName, Date createdDate) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		String s="SELECT userId FROM User \r\n" + 
				"WHERE userName=:userName AND createdDate=:createdDate AND transactionType='Withdraw'";
		Query query=session.createQuery(s);
		query.setParameter("userName", userName).setParameter("createdDate", createdDate);
		List result=query.list();
		return result;
		
	}
	
	@Override
	public List login(String name,String password) {
		Session session1=this.sessionFactory.getCurrentSession();
		String s="select name from Admin \r\n"+
		"where name=:name AND password=:password";
		Query query=session1.createQuery(s);
		query.setParameter("name", name).setParameter("password",password);
		List result=query.list();
		return result;
	}
	@Override
	public List checkCarExist(String carNo) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Parking where carNo=:carNo AND exitTime=NULL";
		Query query=session.createQuery(s);
		query.setParameter("carNo", carNo);
		List result=query.list();
		return result;
	}
	
	@Override
	public Parking carInfo(String floorName,String slotName) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Parking \r\n"+
		          "where floorName=:floorName AND slotName=:slotName order by pid desc";
		Query query=session.createQuery(s);
		query.setParameter("floorName", floorName);
		query.setParameter("slotName", slotName);
		query.setMaxResults(1);
        return (Parking)query.uniqueResult();	
	}
	@Override
	public Parking searchCarNo(String carNo) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="from Parking where carNo=:carNo AND exitTime=NULL";
		Query query=session.createQuery(s);
		query.setParameter("carNo", carNo);
		return (Parking)query.uniqueResult();
	}
	@Override
	public List checkOccupySlot(int fid) {
		Session session=this.sessionFactory.getCurrentSession();
		String s="select slotName from Slot where fid=:fid AND state='occupy'";
		Query query=session.createQuery(s);
		query.setParameter("fid", fid);
		List result=query.list();
		return result;
	}
	//hmh
			public Long listOccupySlots(int floorid){
				Session session=this.sessionFactory.getCurrentSession();
				String s="select count(*) from Slot\r\n"+
						 "where fid=:floorid AND state='occupy'";
				Query query=session.createQuery(s);
				query.setParameter("floorid", floorid);
				
				return ((Long) query.uniqueResult());
			}
			public Long listAvailableSlots(int floorid){
				Session session=this.sessionFactory.getCurrentSession();
				String s="select count(*) from Slot\r\n"+
						 "where fid=:floorid AND state='available'";
				Query query=session.createQuery(s);
				query.setParameter("floorid", floorid);
				
				return (Long) query.uniqueResult();
			}
			public Long listDisableSlots(int floorid){
				Session session=this.sessionFactory.getCurrentSession();
				String s="select count(*) from Slot\r\n"+
						 "where fid=:floorid AND state='disable'";
				Query query=session.createQuery(s);
				query.setParameter("floorid", floorid);
				
				return ((Long) query.uniqueResult());
			}
	
}
