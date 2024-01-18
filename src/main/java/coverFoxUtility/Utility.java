package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class Utility {
	public static String readDataFromExcel(int row, int cell) throws EncryptedDocumentException, IOException {
		FileInputStream myFile=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\TestDataForTestNgProject.xlsx");
Sheet mySheet = WorkbookFactory.create(myFile).getSheet("CoverFox");
	String data = mySheet.getRow(row).getCell(cell).getStringCellValue();
	return data;
	}
	
	public static void takeScreenShot(WebDriver driver,String TCID) throws IOException {
		String timeStamp=  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("F:\\Automation Testing\\Scrrenshots\\"+TCID+timeStamp+".png");
		Reporter.log("Location of Scrrenshot: "+dest, true);
		FileHandler.copy(src, dest);
	}
	public static String readDataFromPropertyFile(String key) throws IOException {
		//Create object of properties class
		Properties prop=new Properties();
		//File Location
		//FileInputStream myFile=new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\SeleniumWithJava\\CoverFox.properties");
	    //Load file
		FileInputStream myFile=new FileInputStream(System.getProperty("user.dir")+"\\CoverFox.properties");
	    prop.load(myFile);
	    String value = prop.getProperty(key);
	    return value;
	}
	
}
