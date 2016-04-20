package com.appserver.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



@SuppressWarnings("unchecked")   
public class BaseDAOIpl<T> implements BaseDAO<T> {   
  
    private Class<T> clazz;   
  
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**  
     * ͨ���췽��ָ��DAO�ľ���ʵ����  
     */  
    public BaseDAOIpl() {   
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();   
        clazz = (Class<T>) type.getActualTypeArguments()[0];   
        System.out.println("DAO  :" + this.clazz.getName());   
    }   
  
    private SessionFactory sessionFactory;   
  
    protected Session getSession() {   
        return this.sessionFactory.getCurrentSession();   
    }   
  
    public void save(T entity) {   
    	this.getSession().beginTransaction();
        this.getSession().save(entity);  
        this.getSession().getTransaction().commit();
    }   
  
    public void update(T entity) {   
    	this.getSession().beginTransaction();
        this.getSession().update(entity);   
        this.getSession().getTransaction().commit();
    }   
  
    public void delete(Serializable id) {   
    	Object o=this.findById(id);
    	this.getSession().beginTransaction();
        this.getSession().delete(o);   
        this.getSession().getTransaction().commit();
    }   
  
    public T findById(Serializable id) {   
    	this.getSession().beginTransaction();
        Object t= this.getSession().get(this.clazz, id); 
    	 this.getSession().getTransaction().commit();
        return (T) t;
        
        
    }   
  
    public List<T> findByHQL(String hql, Object... params) {   
    	this.getSession().beginTransaction();
        Query query = this.getSession().createQuery(hql);   
        for (int i = 0; params != null && i < params.length; i++) {   
            query.setParameter(i, params[i]);
        }   
        List<T> list=query.list(); 
        this.getSession().getTransaction().commit();
        return list;
    }

	@Override
	public List<?> findListByHqlAndPage(String hql,int page,int size,Object...params){
		int resultSize=size;
		this.getSession().beginTransaction();
        Query query = this.getSession().createQuery(hql);   
        for (int i = 0; params != null && i < params.length; i++) {   
            query.setParameter(i, params[i]);
        }
        query.setFirstResult(resultSize*(page-1));
        query.setMaxResults(resultSize);
        List<T> list=query.list(); 
        this.getSession().getTransaction().commit();
        return list;
	}
}
