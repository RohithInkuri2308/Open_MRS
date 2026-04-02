package Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtenReporter {
	
	public static ExtentReports generateExtenreport(String URL, String Browser,String UserName, String password) {
		ExtentReports extent=new ExtentReports();
		
		File reportDir = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports");
		
		reportDir.mkdirs();
	    
		File extentReportFile=new File(reportDir+"\\extentreport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("OPEN MRS Test Automation Results");
		sparkReporter.config().setDocumentTitle("Open MRS Date validation");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application URL",URL);
		extent.setSystemInfo("Browser", Browser);
		extent.setSystemInfo("UserName",UserName);
		extent.setSystemInfo("Password", password);
		extent.setSystemInfo("Operanting system",System.getProperty("os.version"));
		extent.setSystemInfo("Java version",System.getProperty("java.version"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		
		return extent;
	}

}
