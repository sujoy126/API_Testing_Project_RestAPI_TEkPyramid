package com.ninza.hrm.api.employeetest;

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
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.genericutility.PropertyFileUtility;
import com.ninza.hrm.api.pojoclasses.ProjectPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ProjectTest extends BaseAPIClass {
	

	String projectName;
	ProjectPojo pobj;
	
	
	@Test
	public void addSingleProjectWithCreated() throws Throwable {
		
	//	 String baseUri = pfLib.getDataFromPropertiesFile("BASEUri");
		 String expSucMsg ="Successfully Added";
		      projectName ="MissionImposible_"+jLib.getRandomNumber(5000);
		
		             pobj = new ProjectPojo(projectName, "created", 0, "Sujoy");             

		//Verify the projectName in API layer
	Response rep = 	given()
		                  .spec(reqspec)
		                  .body(pobj)
		            .when()
		                 .post(IEndPoint.ADDProj);
	
	          	rep.then()
		                 .assertThat().statusCode(201)
		                 .assertThat().time(Matchers.lessThan(3000L))
		                 .spec(resspec)
		                 .log().all();
	          	
	      String actMsg  = rep.jsonPath().get("msg");
	      Assert.assertEquals(expSucMsg, actMsg);
	      
/*	  //Verify the projectName in Database layer
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
					Assert.assertTrue(flag, "Project in DB is not verified");
	*/
	      
	    //Verify the projectName in Database layer

	      boolean flag = dbLib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
	      Assert.assertTrue(flag, "Project in DB is not verified");
	      	      
	}
	
	@Test(dependsOnMethods = "addSingleProjectWithCreated")
	public void createDublicateProjectTest() throws Throwable {
	//	String baseUri = pfLib.getDataFromPropertiesFile("BASEUri");
		given()
        .spec(reqspec)
        .body(pobj)
  .when()
       .post(IEndPoint.ADDProj)
   .then()
       .assertThat().statusCode(409)
       .spec(resspec)
         .log().all();	
	}
	
	
}

	


