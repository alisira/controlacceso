package com.mf.springapp.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mf.springapp.service.PriceIncrease;
import com.mf.springapp.service.ProductManager;

@Controller
@RequestMapping(value="/priceincrease.htm")
public class PriceIncreaseFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductManager productManager;

    @RequestMapping(method = RequestMethod.POST)
    //public String onSubmit(@Valid PriceIncrease priceIncrease, BindingResult result)
    public ModelAndView handleRequest(@Valid PriceIncrease priceIncrease, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String respuesta = null;
    
        if (result.hasErrors()) {
        	respuesta = "error";
        }
        System.out.println(1);
        int increase = priceIncrease.getPercentage();
        logger.info("Increasing prices by " + increase + "%.");

        productManager.increasePrice(increase);
        respuesta = Integer.toString(increase);

        //return "redirect:/hello.htm";
        return new ModelAndView("prueba", "laHora", respuesta);
  
    }

    @RequestMapping(method = RequestMethod.GET)
    protected PriceIncrease formBackingObject(HttpServletRequest request) throws ServletException {
    	System.out.println(2);
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(15);
        return priceIncrease;
    }

    public void setProductManager(ProductManager productManager) {
        System.out.println(3);
    	this.productManager = productManager;
    }

    public ProductManager getProductManager() {
    	System.out.println(4);
        return productManager;
    }
  
    @RequestMapping(value="/priceincrease.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println(5);
    	String laHora = (new Date()).toString();
    	logger.info("Returning hello view with " + laHora);
    	 
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", laHora);
        myModel.put("products", this.productManager.getProducts());
        
        return new ModelAndView("prueba", "laHora", laHora);

    }
    

}