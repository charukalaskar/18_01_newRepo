package coverFoxTest;
//Check coverFoxBase,coverFoxPOM,coverFoxTest,coverFoxUtility
import org.testng.annotations.Test;

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

@Listeners(listners.ListnerCoverFox.class)
public class CF_TC01_Validate_search_results_for_healthcare_policies2 extends Base {
	// Declaraing

	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPlanResultsPage result;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		launchCoverFox();
		// initalizing
		home = new CoverFoxHomePage(driver);
		healthPlan = new CoverFoxHealthPlanPage(driver);
		addressDetails = new CoverFoxAddressDetailsPage(driver);
		memberDetails = new CoverFoxMemberDetailsPage(driver);
		result = new CoverFoxHealthPlanResultsPage(driver);

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
		memberDetails.hanldeAgeDropDown(Utility.readDataFromPropertyFile("age"));
		Reporter.log("Clicking on next button ", true);
		memberDetails.clickOnNextButton();
		Thread.sleep(1000);
		Reporter.log("Entering pin code ", true);
		addressDetails.enterPinCode(Utility.readDataFromPropertyFile("Pin"));
		Reporter.log("Entering mobile num ", true);
		addressDetails.enterMobNum(Utility.readDataFromPropertyFile("Mob"));
		Reporter.log("Clicking on continue button ", true);
		addressDetails.clickOnContinueButton();
		Thread.sleep(1000);

	}

	//here we failed TC to see result of 2nd TC only
//	@Test (enabled = false)
	@Test 
	public void validateTestPlansFromTextAndBanners() throws InterruptedException, IOException {
		Thread.sleep(5000);
		Reporter.log("Fetching number of results from text ", true);
		int textResult = result.availablePlanNumberFromText();
		Thread.sleep(7000);
		Reporter.log("Fetching number of results from Banners ", true);
		int bannerResult = result.availablePlanNumberFromBanners();
		Thread.sleep(1000);
		Assert.assertEquals(textResult, bannerResult,"Text results are matching with Banner results, TC is failed");
		Reporter.log("TC is passed ", true);
		//Utility.takeScreenShot(driver, "TC01_"); 
	}

	//@Test
	public void validateSortPlanButton() {
	 	 Assert.assertTrue(result.sortPlanElementPresency(),"Sort Plan Field not present");
	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		closeCoverFox();
		Thread.sleep(3000);

	}

}
