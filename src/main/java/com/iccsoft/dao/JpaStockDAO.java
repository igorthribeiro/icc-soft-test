package com.iccsoft.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.iccsoft.dominio.Stock;

@Repository  
public class JpaStockDAO {

	@PersistenceContext  
    EntityManager manager; 
	
	
	public void post(Stock stock) {
		if (stock.getName() == null) {
			throw new StockException("Nome não pode ser nulo.");
		}
		if (readByName(stock.getName()) != null) {
			throw new StockException("Nome já foi usado.");
		}
		manager.persist(stock);
	}
	
	public void path(Stock stock) {
		manager.merge(stock);
	}
	
	public void delete(Stock stock) {
		manager.remove(stock);
	}
	
	public Stock readByName(String name) {
		try {
			return manager.createQuery("select s from Stock s where s.name = :name", Stock.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}	
		
	}
	
	public List<Stock> readAll() {
		return manager.createQuery("select s from Stock s order by s.name", Stock.class).getResultList();
	}
	
}
