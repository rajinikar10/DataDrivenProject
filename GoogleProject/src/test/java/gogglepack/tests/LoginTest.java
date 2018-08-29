package gogglepack.tests;

import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import gogglepack.base.BaseTest;
import googlepack.util.DataUtil;
import googlepack.util.ExtentManager;
import googlepack.util.MyXLSReader;

public class LoginTest extends BaseTest {
	
	MyXLSReader xls;
	
	@BeforeClass
	public void init() {
		
		initialise();
		
	}
	
	@DataProvider
	public Object[][] getData() throws Exception{
		
		String filePath = prop.getProperty("xlsxFilePath");
		
	    xls = new MyXLSReader(filePath);
	    
	    Object[][] testData = DataUtil.getTestData(xls,"LoginTest","Data");
		
	    return testData;
	}
	
	@Test(dataProvider="getData")
	public void doLoginTest(HashMap<String,String> map) {
		
		eTest = eReport.startTest("LoginTest");
		
		eTest.log(LogStatus.INFO, "Login test started");
		
		if(!DataUtil.isRunnable(xls,"LoginTest","Testcases") || map.get("Runmode").equals("N")){
			
			eTest.log(LogStatus.SKIP, "Skipping the test as the run mode is set to N");
			throw new SkipException("Skipping the test as the run mode is set to N");
			
		}	
		
		openBrowser(map.get("Browser"));
		
		navigate("appURL");
		
		boolean actualResult = doLogin(map.get("Username"),map.get("Password"));
			
	    boolean expectedResult = false;
	    
	    if(map.get("ExpectedResult").equals("Success")) {
	    	
	    	expectedResult = true;
	    	
	    }else {
	    	
	    	expectedResult = false;
	    	
	    }
			
		if(actualResult!=expectedResult) {
			
			reportFail("Login test failed");
			
		}else {
			
			reportPass("Login test passed");
			
		}
		
	}
	
	 @AfterMethod
		public void testClosure() {
			
			eReport.endTest(eTest);
			
			eReport.flush();
			
			if(driver!=null) {
			driver.quit();
			}
		}
}
