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

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class CF_TC01_Validate_search_results_for_healthcare_policies extends Base {
	public static Logger logger;
	
	// Declaraing

	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPlanResultsPage result;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		logger= logger.getLogger("CoverFoxInsurance");
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("launchCoverFox");
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
		logger.info("clicking on gender button ");
		home.clickOnFemaleButton();
		Thread.sleep(1000);
		Reporter.log("clicking on next button ", true);
		logger.info("clicking on next button ");
		healthPlan.clickOnNextButton();
		Thread.sleep(1000);
		Reporter.log("Handeling age drop down ", true);
		logger.info("Handeling age drop down ");
		memberDetails.hanldeAgeDropDown(Utility.readDataFromExcel(1, 0));
		Reporter.log("Clicking on next button ", true);
		logger.info("Clicking on next button  ");
		memberDetails.clickOnNextButton();
		Thread.sleep(1000);
		Reporter.log("Entering pin code ", true);
		logger.info("Entering pin code  ");
		addressDetails.enterPinCode(Utility.readDataFromExcel(1, 1));
		Reporter.log("Entering mobile num ", true);
		logger.info("Entering mobile num");
		addressDetails.enterMobNum(Utility.readDataFromExcel(1, 2));
		Reporter.log("Clicking on continue button ", true);
		logger.error("Clicking on continue button ");
		addressDetails.clickOnContinueButton();
		Thread.sleep(1000);

	}

	@Test
	public void validateTestPlansFromTextAndBanners() throws InterruptedException, IOException {
		Thread.sleep(5000);
		Reporter.log("Fetching number of results from text ", true);
		logger.info("Fetching number of results from text ");
		int textResult = result.availablePlanNumberFromText();
		Thread.sleep(7000);
		Reporter.log("Fetching number of results from Banners ", true);
		logger.info("Fetching number of results from Banners ");
		int bannerResult = result.availablePlanNumberFromBanners();
		Thread.sleep(1000);
		logger.info("Fetching number of results from Banners ");
		Assert.assertEquals(textResult, bannerResult,"Text results are not matching with Banner results, TC is failed");	
		Utility.takeScreenShot(driver, "TC01_");
	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		closeCoverFox();
		Thread.sleep(3000);

	}

}
