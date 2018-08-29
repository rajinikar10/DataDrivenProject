package gogglepack.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class test {
	
	@Test
	public void launch(){
		 
	  WebDriver driver = new ChromeDriver();
	
	  System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
	  driver.get("http://google.com");
	}
}
