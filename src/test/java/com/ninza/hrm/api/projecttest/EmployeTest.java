package com.ninza.hrm.api.projecttest;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;
import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.constants.endpoints.IEndPoint;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.PropertyFileUtility;
import com.ninza.hrm.api.pojoclasses.EmployePOJO;
import com.ninza.hrm.api.pojoclasses.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeTest extends BaseAPIClass {
	 	
	@Test
	public void addEmployeTest() throws Throwable {
	
	//	 String baseUri = pfLib.getDataFromPropertiesFile("BASEUri");
			
		
		String projectName ="HerryPorter_"+ jLib.getRandomNumber(5000);
		String userName = "Sujoy_"+jLib.getRandomNumber(3000);
	  // API-1 :=> Add project in side server
		ProjectPojo pp = new ProjectPojo(projectName, "Created", 0, "Sujoy");

	            	given()
		              .spec(reqspec)
		              .body(pp)
		           .when()
		              .post(IEndPoint.ADDProj)  
	               .then()
	                  .spec(resspec)
			          .log().all();
	     
	   
	          //API-2 ==>: add employee to same project
	              
	      EmployePOJO empobj = new EmployePOJO("SDET", "12/07/2024","sujoy@gmail.com", userName, 3.8,
	    		                                "1234567890", projectName, "Automation",userName);
	                             given()
	            		            .spec(reqspec)
	            		            .body(empobj)
	            		        .when()
	            		            .post(IEndPoint.ADDEmp)
	                            .then()
	                                .assertThat().statusCode(201)                                
	                                .and()
	                                .time(Matchers.lessThan(3000L))
	                                .spec(resspec)
	                                .log().all();
	                 
/*	         //verify the Employee name in database
	                             boolean flag = false;
	                 			Driver driverReg = new Driver();
	                 			DriverManager.registerDriver(driverReg);
	                 			Connection con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root@%", "root");
	                 			Statement stat= con.createStatement();
	                 			ResultSet resultSet = stat.executeQuery("select * from project");
	                 			while(resultSet.next()) {			
	                 	//		System.out.println(resultSet.getString(4));
	                 				if(resultSet.getString(4).equals(projectName)) {
	                 					flag = true;
	                 					break;
	                 				}
	                 			}
	                 					con.close();
	                 					Assert.assertTrue(flag, "Employee in DB is not verified");
*/
	          //verify the Employee name in database 
	                             
	                boolean flag = dbLib.executeQueryVerifyAndGetData("select * from employee", 5, userName);
	                Assert.assertTrue(flag, "Project in DB is not verified");
	                Assert.assertTrue(flag, "Employee in DB is not verified");	                
	                 	}
	
	@Test
	public void addEmployeWithoutEmailTest() throws Throwable {
	
	//	 String baseUri = pfLib.getDataFromPropertiesFile("BASEUri");
			
	
		
		String projectName ="HerryPorter_"+ jLib.getRandomNumber(4000);
		String userName = "Sujoy_"+jLib.getRandomNumber(5000);
	  // API-1 :=> Add project in side server
		ProjectPojo pp = new ProjectPojo(projectName, "Created", 0, "Sujoy");

	            	given()
		              .spec(reqspec)
		              .body(pp)
		           .when()
		              .post(IEndPoint.ADDProj)  
	               .then()
	                  .spec(resspec)
			          .log().all();
	     
	   
	          //API-2 ==>: add employee to same project
	              
	      EmployePOJO empobj = new EmployePOJO("SDET", "12/07/2024","", userName, 3.8,
	    		                                "1234567890", projectName, "Automation",userName);
	                             given()
	            		            .spec(reqspec)
	            		            .body(empobj)
	            		        .when()
	            		            .post(IEndPoint.ADDEmp)
	                            .then()
	                                .assertThat().statusCode(500)
	                                .spec(resspec)                    
	                                .log().all();	                 	                  		     
	                 	}
	
	
	
		}
	


