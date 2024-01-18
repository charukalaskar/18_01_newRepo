package coverFoxTest;
//Check coverFoxBase,coverFoxPOM,coverFoxTest,coverFoxUtility
//Verifying Error Massage visibility and Error Massage Content for Pincode field
//Negative Scenario

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHealthPlanResultsPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

@Listeners(listners.ListnerCoverFox2.class)
public class CF_TC02_Validate_Pincode extends Base {
	// Declaraing

	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxAddressDetailsPage addressDetails;
	//CoverFoxHealthPlanResultsPage result;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		launchCoverFox();
		// initalizing
		home = new CoverFoxHomePage(driver);
		healthPlan = new CoverFoxHealthPlanPage(driver);
		addressDetails = new CoverFoxAddressDetailsPage(driver);
		memberDetails = new CoverFoxMemberDetailsPage(driver);
		//result = new CoverFoxHealthPlanResultsPage(driver);

	}

	@BeforeMethod
	public void enterMemeberDeatils() throws InterruptedException, EncryptedDocumentException, IOException {
		Reporter.log("clicking on gender button ", true);
		home.clickOnFemaleButton();
		Thread.sleep(1000);
		Reporter.log("clicking on next button ", true);
		healthPlan.clickOnNextButton();
		Thread.sleep(1000);
		Reporter.log("Handeling age drop down ", true);
		
		memberDetails.hanldeAgeDropDown(Utility.readDataFromExcel(1, 0));
		Reporter.log("Clicking on next button ", true);
		memberDetails.clickOnNextButton();
		Thread.sleep(1000);
		Reporter.log("Entering pin code ", true);
		addressDetails.enterPinCode(Utility.readDataFromExcel(0, 0));
		
		Reporter.log("Entering mobile num ", true);
		addressDetails.enterMobNum(Utility.readDataFromExcel(1, 2));
		
		Reporter.log("Clicking on continue button ", true);
		addressDetails.clickOnContinueButton();
		Thread.sleep(2000);
	}

	@Test
	public void validatePincodeErrorMassage() throws InterruptedException, IOException {
		Thread.sleep(5000);
		SoftAssert soft=new SoftAssert();
		Reporter.log("Checking error Massage Visibility ", true);
		Assert.assertTrue(addressDetails.errorMsgForPinVisibility(),"Error Massage is not Visible");
		//soft.assertFalse(addressDetails.errorMsgForPinVisibility(),"Error Massage is Visible");	
		Reporter.log("PinCode Error Msg Content Verification ", true);
		//soft.assertNotEquals(addressDetails.errorMsgForPinContentVerification(), "Please enter a valid pincode","Error Massage Content is Correct as per User Story!");
		//soft.assertAll();
	}


	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		closeCoverFox();
		Thread.sleep(3000);
	}
}
