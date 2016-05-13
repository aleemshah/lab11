/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.util;

import attendance.entity.Attendance;
import attendance.entity.Course;
import javax.imageio.spi.ServiceRegistry;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author oBii
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    public Session currSession = null;

public void printCourse(){
		Query query=currSession.createQuery("from Employee");  
    		List<Course> list=query.list();  
      
   		Iterator<Course> itr=list.iterator();  
		while(itr.hasNext()){  
			Course emp=itr.next();  
			System.out.println("Emp Name: "+emp.getFirstName());  
    		}  
	}

    static {
        try {
            Configuration cfg=new Configuration();  
            cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
		//creating seession factory object  
		SessionFactory factory=cfg.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
                
                serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings( cfg.getProperties()).build();

                Transaction t=currSession.beginTransaction();  
			Attendance e1=new Attendance();  
			e1.setId(115);   
			currSession.persist(e1);//persisting the object  
			t.commit();//transaction is commited  
                        session.close(); 
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
