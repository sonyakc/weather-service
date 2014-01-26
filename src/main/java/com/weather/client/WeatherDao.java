package com.weather.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.weather.domain.Forecast;

public class WeatherDao implements IWeatherDao {
	private Session session;
	
	public WeatherDao() {
		Configuration  cfg = new Configuration();
		cfg.addAnnotatedClass(Forecast.class);
		cfg.configure(); // Reads the hibernate.cfg.xml configuration file
		
		// Makes Hibernate create the tables
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.create(true, true); // Execute SQL statements to the database
		
		// Session factory is a resource intensive object
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		session = factory.openSession();
	}
	
	@Override
	public Forecast create(Forecast forecast) {
		// begin txn
		Transaction tx = session.beginTransaction();
				
		session.persist(forecast);
		
		tx.commit();
		session.close();
		return forecast;
	}

	@Override
	public List<Forecast> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
