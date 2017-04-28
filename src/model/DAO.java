/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

/**
 *
 * @author khonvt
 */
public class DAO {
    SessionFactory factory;
    Session session;
    public DAO(){
         factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
         session = factory.openSession();
    }
    
    public void saveOrUpdate(Object obj){
        session.saveOrUpdate(obj);
    }
    
    public void Update(Object obj){
        session.saveOrUpdate(obj);
    }
    
    public void beginTransaction(){
        session.beginTransaction();
    }
    
    public void closeAll(){
        session.close();
        factory.close();
    }
    
    public void commitTransaction(){
        session.getTransaction().commit();
    }
    
    public Object getObject(Object obj,int id){     
        return session.get(obj.getClass(),id);
    }
    
    public void flush(){
        session.flush();
    }
    
    public void clear(){
        session.clear();
    }
    
    public Object getObject(Object obj,String id){
        return session.get(obj.getClass(),id);
    }
    
    public List getAllObjects(String className){
        return session.createQuery("from "+className).list();
    }
    
    public void DeleteObject(Object obj){
        session.delete(obj);
    }
    
    public List getObjectsWithCriteria(String objName, String crit){
        
        return session.createQuery("from "+objName+" e where "+crit).list();
        
    }
    
    public void saveObject(Object obj){
        
        session.save(obj); 
        
    }
    
    public void saveObjects(List list){
        list.forEach(e->session.save(e));
    }
    
    public List DistinctQuery(Object obj , String columnName){
        Criteria crit =session.createCriteria(obj.getClass());
        crit.setProjection(Projections.distinct(Projections.property(columnName)));        
        return crit.list();
    }
     public Object loadObject(Object obj,int id){     
        return session.load(obj.getClass(),id);
    }
    
    
}
