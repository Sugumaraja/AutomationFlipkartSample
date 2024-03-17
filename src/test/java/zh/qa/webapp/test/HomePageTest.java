package zh.qa.webapp.test;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.testng.AllureTestNg;
import zh.qa.webapp.baseTest.BaseTest;
import zh.qa.webapp.constants.AppConstants;

@Listeners(AllureTestNg.class)
@Epic("Home Page of the APP")
@Story("Developing the Homepage and Login function")
@Feature("Home page & Login page function")
public class HomePageTest extends BaseTest{

@Description("To validate the navigation of the login button")
@Test 
public void LoginNavigationTest() {
	 Assert.assertTrue(homepage.isLoginButtonNavigating().equals(AppConstants.LOGINPAGE_CONTENT),"The loginpage content not matching");
}
@Description("To validate the OTP button presence in UI")
@Test 
public void isOTPbtnPresentTest() {
	 Assert.assertTrue(homepage.isOTPbtnPresent(),"The OTP button is not present"); 
}
@Description("To validate the OTP button spell checking")
@Test 
public void OTPbuttonTextMatchTest() {
	 Assert.assertTrue(homepage.isOTPbtnContentCheck().equals(AppConstants.OTPBUTTON_CONTENT),"The OTP button text is wrong"); 
}

}
