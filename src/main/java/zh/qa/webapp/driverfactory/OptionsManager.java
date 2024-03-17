package zh.qa.webapp.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private ChromeOptions co;
	private EdgeOptions eo;
	private FirefoxOptions fo;
	Properties prop;
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}

	public ChromeOptions getChromeOption() {
		co=new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("incognito").toLowerCase().trim())) {
			co.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").toLowerCase().trim())) {
			co.addArguments("--headless");
		}
		return co;
	}
	public EdgeOptions getEdgeOption() {
	eo=new EdgeOptions();
	if (Boolean.parseBoolean(prop.getProperty("incognito").toLowerCase().trim())) {
		eo.addArguments("--inPrivate");
	}
	if (Boolean.parseBoolean(prop.getProperty("headless").toLowerCase().trim())) {
		eo.addArguments("--headless");
	}
	return eo;
}
	public FirefoxOptions getFirefoxOption() {
		fo=new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("incognito").toLowerCase().trim())) {
			fo.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").toLowerCase().trim())) {
			fo.addArguments("--headless");
		}
		return fo;
	}
	
}
