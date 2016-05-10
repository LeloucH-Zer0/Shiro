package com.snapdeal.qaas.shiro.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ejb.Stateless;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
@Stateless
@Path("/ctofservice")
public class CtoFService {
	Logger LOG = LoggerFactory.getLogger(CtoFService.class);
	
	
	@GET
	@Produces("application/xml")
	public String convertCtoF() {
		LOG.info("Reached the API Call");
		if (!SecurityUtils.getSubject().hasRole("admin")){
	           StringBuilder exBuilder = new StringBuilder();
	           exBuilder.append("User: \'");
	           exBuilder.append(SecurityUtils.getSubject().getPrincipal().toString());
	           exBuilder.append("\' is not authorized to use this resource");
	           LOG.info("\n\n YOU DO NOT HAVE ANY RIGHT TO ACCESS THIS !!!! \n\n");
	           throw new SecurityException(exBuilder.toString());
	    }
 
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		StringBuilder result = new StringBuilder();
        result.append("Hello ");
        result.append(fahrenheit);
        result.append(SecurityUtils.getSubject().getPrincipal().toString());
 
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
}