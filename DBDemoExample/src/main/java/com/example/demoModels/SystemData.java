package com.example.demoModels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_data")
public class SystemData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sysid;
	private String platform;
	private String model;
	
	
	public int getSysid() {
		return sysid;
	}
	public void setSysid(int sysid) {
		this.sysid = sysid;
	}
	
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "SystemData [sysid=" + sysid + ", platform=" + platform + ", model=" + model + "]";
	}
	
}

