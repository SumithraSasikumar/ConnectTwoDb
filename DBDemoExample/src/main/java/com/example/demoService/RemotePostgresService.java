package com.example.demoService;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoDao.RemotePostgresDao;
import com.example.demoModels.RemoteData;

@Service("remotePostgresService")
public class RemotePostgresService {

	@Autowired
	RemotePostgresDao remotePostgresDao;
	
	 
	 public List<RemoteData> getRemoteDetails() {
			return remotePostgresDao.getRemoteDetails();
		}

	public Object saveLocalRemoteData(RemoteData data) {
		if(data!=null){
			remotePostgresDao.save(data);
			return "remote Data Saved Successfully";
		}else{
			return "no data";
		}
	}
}
