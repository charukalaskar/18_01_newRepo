package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CoverFoxAddressDetailsPage
{
@FindBy(xpath = "(//input[@type='number'])[1]") private WebElement pinCodeField;
@FindBy(xpath = "(//input[@type='number'])[2]") private WebElement mobNumField;
@FindBy(xpath = "//div[text()='Continue']") private WebElement continueButton;
@FindBy(xpath = "//div[@class='error-ui']") private WebElement errorMsgFieldForPinCode;
@FindBy(xpath = "//div[@class='want-expert__error error-ui']") private WebElement errorMsgFieldForMb;
public CoverFoxAddressDetailsPage(WebDriver driver)
{
PageFactory.initElements(driver, this);
}
public void enterPinCode(String pincode)
{
pinCodeField.sendKeys(pincode);
}
public void enterMobNum(String mobnum)
{
mobNumField.sendKeys(mobnum);
}

public void clickOnContinueButton()
{
continueButton.click();
}
public boolean errorMsgForPinVisibility() {
	boolean status = errorMsgFieldForPinCode.isDisplayed();
	return status;
}
public String errorMsgForPinContentVerification() {
	String textOfErrorMsg = errorMsgFieldForPinCode.getText();
	return textOfErrorMsg ;
	
}
public boolean errorMsgForMbVisibility() {
	boolean status = errorMsgFieldForMb.isDisplayed();
	return status;
}
public String errorMsgForMbContentVerification() {
	String textofErrorMsgOfPass = errorMsgFieldForMb.getText();
	return textofErrorMsgOfPass;
}
}