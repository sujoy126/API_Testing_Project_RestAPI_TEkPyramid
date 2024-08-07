package com.ninza.hrm.api.baseClass;

import java.sql.SQLException;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.genericutility.PropertyFileUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	
	 public JavaUtility jLib = new JavaUtility();
	 public PropertyFileUtility pfLib = new PropertyFileUtility();
	 public DataBaseUtility dbLib = new DataBaseUtility();
	 public static RequestSpecification reqspec;
	 public static ResponseSpecification resspec;
	 
	 @BeforeSuite
		public void configBSuite() throws Throwable {
		      dbLib.getConnection();
		      System.out.println("============connect============");
		      RequestSpecBuilder builder = new RequestSpecBuilder();
		      builder.setContentType(ContentType.JSON);
   	  //      builder.setAuth(basic("username", "password"));
	//	      builder.addHeader("", "");
		      builder.setBaseUri(pfLib.getDataFromPropertiesFile("BASEUri"));
		      reqspec = builder.build(); 
		      
		      ResponseSpecBuilder responseSpec  = new ResponseSpecBuilder();
		      responseSpec.expectContentType(ContentType.JSON);
		      resspec = responseSpec.build();		                 		      
		}
	 
	 @AfterSuite
		public void configASuite() throws SQLException {
			dbLib.closeDataBaseConnection();
			System.out.println("=========disconnect==========");
		}

}
