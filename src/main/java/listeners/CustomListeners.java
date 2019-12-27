package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.TestBase.Base;

import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.Utilities;

public class CustomListeners extends Base implements ITestListener, ISuiteListener {
	public CustomListeners() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String messageBody;

	public void onTestStart(ITestResult result) {

		currenttest = report.startTest(result.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {

		currenttest.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		report.endTest(currenttest);
		report.flush();
	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");

		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}

		currenttest.log(LogStatus.FAIL,
				result.getName().toUpperCase() + "FAILED with EXCEPTION " + result.getThrowable());
		currenttest.log(LogStatus.FAIL, currenttest.addScreenCapture(Utilities.ScreenShotName));

		Reporter.log("Click to see the screenshot");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.ScreenShotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.ScreenShotName + ">" + "<img src="
				+ Utilities.ScreenShotName + " height=200 width=200></img></a>");

		report.endTest(currenttest);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {

		currenttest.log(LogStatus.SKIP, result.getName().toUpperCase() + " Skipped the test.");
		report.endTest(currenttest);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {
		
		/*MonitoringMail mail = new MonitoringMail();
		 
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/LiveProject%20-%20PageObjectModel/Extent_Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
