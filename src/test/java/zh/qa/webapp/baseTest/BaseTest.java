package zh.qa.webapp.baseTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import zh.qa.webapp.driverfactory.DriverFactory;
import zh.qa.webapp.pages.HomePage;
import zh.qa.webapp.pages.SearchResultPage;

public class BaseTest {

	protected DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	protected HomePage homepage;
	protected SearchResultPage srchpage;

	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName){
		df=new DriverFactory();
		prop=df.initProp();
		if(browserName!=null) {
			System.out.println("browser name is : "+ browserName);
			prop.setProperty("browser", browserName);
		}
		driver=df.initBrowser(prop);
		homepage=new HomePage(driver);
	}
	@AfterTest
	public void tearDown(){
	driver.quit();
	}
}
