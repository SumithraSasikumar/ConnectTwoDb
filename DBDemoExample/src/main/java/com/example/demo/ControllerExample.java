package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demoModels.RemoteData;
import com.example.demoModels.SystemData;
import com.example.demoService.SystemService;
import com.example.demoService.RemotePostgresService;
import com.example.demoService.RemoteService;


@Controller
@RequestMapping("save")
public class ControllerExample {
	@Autowired
	SystemService systemService;
	@Autowired
	RemoteService remoteService;
	
	@Autowired
	RemotePostgresService remotePostgresService;

	@RequestMapping(value = "/saveSystemData", method = RequestMethod.POST)
	public @ResponseBody Object saveSystemData(@RequestBody SystemData Data) {
try{
		Object admin = systemService.saveSystemData(Data);
		return admin;
		
	}catch(Exception e){
		String errorMsg="error occured while saving systemData";
	return errorMsg;	
	}
	}
	
	@RequestMapping(value = "/saveLocalRemoteData", method = RequestMethod.POST)
	public @ResponseBody Object saveLocalRemoteData(@RequestBody RemoteData Data) {
try{
		Object admin = remotePostgresService.saveLocalRemoteData(Data);
		return admin;
		
	}catch(Exception e){
		String errorMsg="error occured while saving remoteData";
	return errorMsg;	
	}
	}
	
	@RequestMapping(value = "/getRemoteData", method = RequestMethod.GET)
public @ResponseBody Object getRemoteData() {
try{
		Object admin = remotePostgresService.getRemoteDetails();
		return admin;
		
	}catch(Exception e){
		String errorMsg="error occured while getting remoteData";
	return errorMsg;	
	}

}
	@RequestMapping(value = "/saveRemoteData", method = RequestMethod.POST)
	public @ResponseBody void saveRemoteData() {
try{
		 remoteService.saveRemoteDb();
		
	}catch(Exception e){
		e.printStackTrace();	
	}
}
	
	@RequestMapping(value = "/saveMacData", method = RequestMethod.POST)
	public @ResponseBody Object saveMacData(@RequestBody Map<String,Object> Data) {
try{
	Object admin= systemService.saveMacData(Data);
		return admin;
	}catch(Exception e){
		String errorMsg="error occured while saving macData";
		return errorMsg;	
}
	}
	
	@RequestMapping(value = "/getMacData", method = RequestMethod.GET)
	public @ResponseBody Object getMacData() {
try{
	Object admin= systemService.getMacData();
		return admin;
	}catch(Exception e){
		String errorMsg="error occured while getting macData";
		return errorMsg;	
}
	}
}
