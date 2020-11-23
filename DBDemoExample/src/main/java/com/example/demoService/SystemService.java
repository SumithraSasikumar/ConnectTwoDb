package com.example.demoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demoDao.SystemDao;
import com.example.demoModels.MacData;
import com.example.demoModels.RemoteData;
import com.example.demoModels.SystemData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service("systemService")
public class SystemService {
	@Autowired
	SystemDao systemDao;
	
	 ResourceBundle bundle1 = ResourceBundle.getBundle("SystemConfig");
	
	public Object saveSystemData(SystemData data) {
		if(data!=null){
		try{
			
			systemDao.insertData(data);
			}catch(Exception e){
			e.printStackTrace();
		}
		}
		return "SystemData Successfully saved";
	}

	public List<SystemData> getSystemDetails() {
		return systemDao.getSystemDetails();
	}
	
	public Object saveMacData(Map<String, Object> data) {
		MacData mc=null;
		List<Map<String,Object>> macLists=(List<Map<String,Object>>)data.get("macList");
		if (macLists != null && !macLists.isEmpty()) {
			for(Map<String, Object> macData:macLists){
				try{
int id=(Integer)macData.get("id");
System.out.println(id);
				MacData macdata = systemDao.getCheckMacDetails(id);
				System.out.println(macdata);
				if(macdata==null) {
		mc=new MacData();
		mc.setDepdisplay((String)macData.get("depdisplay"));
		mc.setDepname((String)macData.get("depname"));
		mc.setIp((String)macData.get("ip"));
		mc.setPort((String)macData.get("port"));
		mc.setId(id);
		systemDao.save(mc);
		return "MacData saved Successfully";
				}else{
			return "data exist";
		}}catch(NumberFormatException e){
					e.printStackTrace();
				}
	}
			}
		
		return "success";
	}

	public Object getMacData() {
		Map<String,Object> result=new HashMap<String,Object>();
		List<MacData> masters=systemDao.getMacDatas();
		
		List<Object> master=masters.stream().filter(m ->m!=null).collect(Collectors.toList());
		//List<Object> masterParallel=masters.parallelStream().filter(m ->m!=null).collect(Collectors.toList());
		//result.put("macDatasParallel", masterParallel);
	
	
		result.put("macDatas", master);

		
	return result;
	}
	

}