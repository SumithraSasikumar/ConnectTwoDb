package com.example.demoDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoModels.MacData;
import com.example.demoModels.RemoteData;


@Repository("remoteDao")
@Transactional("mysql3TransactionManager")
public class RemoteDao {
	@PersistenceContext(unitName = "thirdPersistence")
    private EntityManager manager;
    
    public void insertData(RemoteData data) {
    	manager.persist(data);

	}

	

	
}
