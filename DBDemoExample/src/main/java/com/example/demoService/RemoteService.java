package com.example.demoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.demoDao.SystemDao;
import com.example.demoDao.RemoteDao;

import com.example.demoModels.RemoteData;
import com.example.demoModels.MacData;
import com.example.demoModels.SystemData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
@Service("remoteService")
public class RemoteService {
	@Autowired
	SystemDao systemDao;
	
	    ResourceBundle bundle1 = ResourceBundle.getBundle("SystemConfig");
	
	public void saveRemoteDb() {
		
			 Map<String,Object> result = new HashMap<String,Object>();
			    List<SystemData> detailsList = systemDao.getSystemDetails();
			    System.out.println(detailsList);
			     Map<String,Object> details=null;
			    
			            if(detailsList !=null && !detailsList.isEmpty()){
			                
			                List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			                for(SystemData sysData:detailsList){
			                    try{
			details=new HashMap<String,Object>();
			details.put("model", sysData.getModel());
			details.put("platform", sysData.getPlatform());
			details.put("sysid", sysData.getSysid());
		
			list.add(details);
       List<JSONObject> jsonObj = new ArrayList<JSONObject>();
       for (Map<String, Object> dataff : list) {
           JSONObject obj = new JSONObject(dataff);
           jsonObj.add(obj);
       }
       JSONArray finalValue = new JSONArray(jsonObj);
       result.put("systemList", finalValue);
       System.out.println(finalValue);
       String output=saveToRemoteDb(result);
       System.out.println("saving data to db"+output);
			                    }catch(Exception e){
			                    	e.printStackTrace();
			                    }
			                }
			            }
   }
	public String saveToRemoteDb(Map<String,Object> result) {
		String serverUrl = bundle1.getString("global.ws.Board.ip") + "/" + bundle1.getString("global.data.create");
		try {
			
			JSONObject objectTest = new JSONObject(result);
			  Client client = Client.create();
	            String input = objectTest.toString();
	          
	            WebResource webResource = client.resource(serverUrl);
	            ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, input);
	            if (response.getStatus() != 200) {
	                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	            }
	            String output = response.getEntity(String.class);
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	
}
