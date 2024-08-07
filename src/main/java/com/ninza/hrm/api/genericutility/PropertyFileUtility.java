package com.ninza.hrm.api.genericutility;

import java.io.FileInputStream;

import java.util.Properties;


public class PropertyFileUtility {
	public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./config_evn_data/configEnvData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data = pobj.getProperty(key);
		
		return data;
	}

}
