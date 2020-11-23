package com.example.demoModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mac_data")
public class MacData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mid;
	private int id;
	private String ip;
	private String depname;
	private String depdisplay;
	private String port;
	
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
	public String getDepdisplay() {
		return depdisplay;
	}
	public void setDepdisplay(String depdisplay) {
		this.depdisplay = depdisplay;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
}
