package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CoverFoxHomePage

{
//@FindBy(xpath = "//div[text()='Male']")private WebElement maleButton;
@FindBy(xpath = "//div[text()='Female']")private WebElement FemaleButton;
public CoverFoxHomePage(WebDriver driver)
{
PageFactory.initElements(driver, this);
}
//public void clickOnMaleButton()
//{
//maleButton.click();
//
//}
public void clickOnFemaleButton()
{
FemaleButton.click();
}
}
