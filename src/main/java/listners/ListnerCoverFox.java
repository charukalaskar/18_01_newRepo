package listners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverFoxBase.Base;
import coverFoxUtility.Utility;

public class ListnerCoverFox extends Base implements ITestListener {
	@Override
	public void onTestSuccess(ITestResult result) {
		//Reporter.log("TC "+result.getName()+" is Passed Successfully!",true);
	}
	@Override
	public void onTestFailure(ITestResult result) {
	//Reporter.log("TC "+result.getName()+" is Failed! Please Recheck!",true);
		try {
			Utility.takeScreenShot(driver,result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
	public void onTestSkipped(ITestResult result) {
	//Reporter.log("TC "+result.getName()+" is skipped! Please check Dependent Method",true);
	}

    @Override
	public void onTestStart(ITestResult result) {
	//Reporter.log("TC "+result.getName()+" Execution is Started!",true);
	}
}
