package com.example.demoDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoModels.RemoteData;
@Repository("remotePostgresDao")
@Transactional("postgresTransactionManager")
public class RemotePostgresDao {

	@PersistenceContext(unitName = "firstPersistence")
    private EntityManager manager;
	

	public List<RemoteData> getRemoteDetails() {
		List<RemoteData> list=	manager.createQuery("select s from RemoteData s",
				RemoteData.class).getResultList();
		return list;
}


	public void save(RemoteData data) {
		manager.persist(data);
		
	}
}
