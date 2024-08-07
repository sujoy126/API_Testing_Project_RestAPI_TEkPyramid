package com.ninza.hrm.api.genericutility;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtility {
	
	public String getDataOnJsonPath(Response resp, String jsonXpath) {
	     List<Object> list = JsonPath.read(resp.asString(), jsonXpath);
	     return list.get(0).toString();
	}
	
	public String getDataOnXmlPath(Response resp, String xmlXpath) {
		return resp.xmlPath().get(xmlXpath);
	}
	
	public boolean verifyDataOnJsonPath(Response resp, String jsonXpath, String expectedData) {
		List<String> list = JsonPath.read(resp.asString(), jsonXpath);
		boolean flag = false;
		for(String str: list) {
			if(str.equals(expectedData)) {
				System.out.println(expectedData+" is available==pass");
				flag= true;
			}
		}
		if(flag==false) {
			System.out.println(expectedData+" is not available==pass");
		}
		return flag;	
	}
	
	

}
