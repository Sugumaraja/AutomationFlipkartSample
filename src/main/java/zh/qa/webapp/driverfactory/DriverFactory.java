package zh.qa.webapp.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import zh.qa.webapp.frameworkException.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager om;
	public static ThreadLocal<WebDriver>tld= new ThreadLocal<WebDriver>();

	public WebDriver initBrowser(Properties prop) {
		
		String browserName = prop.getProperty("browser");
//		String browserName = System.getProperty("browser");
		System.out.println("the browser name passed : " + browserName);
		om=new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
//			driver = new ChromeDriver(om.getChromeOption());
			tld.set(new ChromeDriver(om.getChromeOption()));
			break;
		case "edge":
//			driver = new EdgeDriver(om.getEdgeOption());
			tld.set(new EdgeDriver(om.getEdgeOption()));
			break;
		case "firefox":
//			driver = new FirefoxDriver(om.getFirefoxOption());
			tld.set(new FirefoxDriver(om.getFirefoxOption()));
			break;
		default:
			System.out.println("please pass the right brower" + browserName);
			new FrameworkException("Right browser missing");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("URL"));

		return getDriver();
	}
	public static WebDriver getDriver() {
		return tld.get();
	}
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				System.out.println("env name passed null so running in default env configuration");
				ip = new FileInputStream("./src/test/resources/Config/config_default.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					System.out.println("env name passed QA so running in QA env configuration");
					ip = new FileInputStream("./src/test/resources/Config/config_qa.properties");
					break;
				case "dev":
					System.out.println("env name passed Dev so running in Dev env configuration");
					ip = new FileInputStream("./src/test/resources/Config/config_dev.properties");
					break;

				default:
					System.out.println("please pass the right env name..." + envName);
					throw new FrameworkException("Wrong env name: " + envName);

				}
			}
		} catch (FileNotFoundException e) {
			throw new FrameworkException("The configuration file is not found");
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String TakeScreenShot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
