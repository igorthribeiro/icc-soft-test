package com.iccsoft.controller;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iccsoft.dao.JpaStockDAO;
import com.iccsoft.dominio.Stock;

@CrossOrigin(origins = "*", allowedHeaders = "*")  
@Transactional  
@RestController  
public class RestfulController {

	@Autowired  
    @Qualifier("jpaStockDAO")  
    private JpaStockDAO dao;  

    @RequestMapping(value ="*", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  
    @ResponseBody  
    public List<Stock> getStocks(@RequestParam(required = false) String name) {  
        if (name != null)  {
        	return Arrays.asList(new Stock[]{dao.readByName(name)});  
        }
        return dao.readAll();
    }  
    
    
    @RequestMapping(value ="*", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody  
    public Stock post(@RequestBody Stock stock) {  
         dao.post(stock);
         return stock;
    }  

    @RequestMapping(value = "/{name}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody  
    public Stock update(@PathVariable String name, @RequestBody Stock stock) {
    	Stock stockToUpdate = dao.readByName(name);
    	if (stockToUpdate != null) {
    		stockToUpdate.getQuotes().addAll(stock.getQuotes());
    		dao.path(stockToUpdate);
    		return stockToUpdate;
    	}
    	return null;
    }  
    
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)  
    @ResponseBody  
    public void delete(@PathVariable("name") String name) {  
         Stock stock = dao.readByName(name);  
         dao.delete(stock); 
    }  

}
