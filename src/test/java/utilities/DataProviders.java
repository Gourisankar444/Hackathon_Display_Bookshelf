package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="configData")
	public String[][] getConfigData()throws IOException{
		Properties prop= new Properties();
		prop.load(new FileInputStream("src\\test\\resources\\config.properties"));
		String configData[][]=new String[1][6];
		configData[0][0]=prop.getProperty("email");
		configData[0][1]=prop.getProperty("password");
		configData[0][2]=prop.getProperty("bookshelvesPageTitle");
		configData[0][3]=prop.getProperty("errorPageTitle");
		configData[0][4]=prop.getProperty("homePageTitle");
		configData[0][5]=prop.getProperty("ULPageTitle");
		return configData;
	}
}
