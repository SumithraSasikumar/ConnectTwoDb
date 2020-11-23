package com.example.demoDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoModels.MacData;
import com.example.demoModels.SystemData;


@Repository("systemDao")
@Transactional("mysql2TransactionManager")
public class SystemDao<T> {
	
	@PersistenceContext(unitName = "secondary")
    private EntityManager manager;
    
    public void insertData(SystemData data) {
    	manager.persist(data);

	}

	@SuppressWarnings("unchecked")
	public List<SystemData> getSystemDetails() {
		List<SystemData> list=	manager.createQuery("select s from SystemData s",
			    SystemData.class).getResultList();
		return list;
}
	public MacData getCheckMacDetails(int id) {
	
	MacData list=	manager.createQuery("select s from MacData s where s.id=:id",
				MacData.class).setParameter("id", id).getSingleResult();
//	MacData mc=manager.find(MacData.class, id);
		System.out.println(list);
		return list;
		
		}

		public void save(MacData mc) {
			manager.persist(mc);
			
		}

		public Object getCheckSysid(int sysid) {
//			SystemData mc=manager.find(SystemData.class, id);
			SystemData list=	manager.createQuery("select s from SystemData s where s.sysid=:sysid",
					SystemData.class).setParameter("sysid", sysid).getSingleResult();
			
			System.out.println(list);
			return list;
		}

		public List<MacData> getMacDatas() {
			List<MacData> list=	manager.createQuery("select s from MacData s",
					MacData.class).getResultList();
			return list;
		}
}
