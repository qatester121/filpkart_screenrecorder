package flipkartUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebUtil {

	public WebDriver driver ;
	private ExtentReports extreport;
	private ExtentTest extTest;
	private Properties prop;
	private JavascriptExecutor executor;


	public WebUtil() {

		try {
			readConfigFile();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void doubleclick(WebElement weEle, String msg) {

		Actions act = new Actions(driver);
		try {
			act.doubleClick(weEle).build().perform();
		} catch (StaleElementReferenceException e) {

			act.doubleClick(weEle).build().perform();
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			// java script

		}
		extTest.log(Status.INFO, msg);
	}

	public void mouseover(WebElement weEle, String msg) {

		Actions act = new Actions(driver);
		try {
			act.moveToElement(weEle).build().perform();
		} catch (StaleElementReferenceException e) {

			act.moveToElement(weEle).build().perform();
		} catch (ElementNotInteractableException e) {
			// java script

		}
		extTest.log(Status.INFO, msg);

	}

	public void nevigateBack() {

		driver.navigate().back();

	}
	public void actionsClick(WebElement weEle, String msg) {

		Actions act = new Actions(driver);
		try {
			act.click(weEle).build().perform();
		} catch (StaleElementReferenceException e) {

			act.click(weEle).build().perform();
		} catch (ElementNotInteractableException e) {

		}
		extTest.log(Status.INFO, msg);
	}


	public void click(WebElement weEle, String msg) {

		new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOf(weEle)).click();

		extTest.log(Status.INFO, msg);

	}


	public void jsClick(WebElement e,String msg) {
		executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", e);
		extTest.log(Status.INFO, msg);
	}





	// ================================================ExtentTest===========================================
	public ExtentReports initReports(String reportResultName) {
		ExtentHtmlReporter ExtHtmlRport = new ExtentHtmlReporter(reportResultName);
		ExtHtmlRport.config().setReportName("Function Reports Automation");
		ExtHtmlRport.config().setDocumentTitle("SeleniumPlayGround Functionality Reports");
		extreport = new ExtentReports();
		extreport.setSystemInfo("username", System.getProperty("user.name"));
		extreport.setSystemInfo("OS", System.getProperty("user.os"));
		extreport.setSystemInfo("Envoirment", "QA");
		extreport.attachReporter(ExtHtmlRport);
		return extreport; 
	}

	public void setExtentLogger(String testcaseName) {
		extTest = extreport.createTest(testcaseName);

	}
	public void setextentExt(ExtentReports ext) {
		extreport=ext;		
	}

	public void flush() {
		extreport.flush();
	}

	public void readConfigFile() throws IOException {
		prop = new Properties();

		File file = new File("PropertyFile//Config.properties");
		System.out.println(file.getAbsolutePath());
		FileInputStream fileInputStrObj;

		fileInputStrObj = new FileInputStream(file);

		// prop= new Properties();
		try {
			prop.load(fileInputStrObj);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Properties getconfigProp() {

		return prop;

	}

	public void clickonmultipleElement(List<WebElement> we,String massage) {

		for(WebElement mainmenu:we) {
			mainmenu.click();

		}}



	// 3=========================Launch Browser==========================================//

	public WebDriver LaunchBrowser(String browsername, String msg) {
		if (browsername.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		return driver;
	}


	public WebDriver getWebDriver() {
		return driver;
	}
	
	

	// ====================================Get Url// Method==================================================//
	public void getUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	  public void openURL(String url) {
	        driver.get(url);
	    }

	    public  void waitForElementClickable(WebElement locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }

	    public  void waitForElementVisibility(WebElement locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait.until(ExpectedConditions.visibilityOf(locator));
	    }
	    public  void hoverOnElectronicsLnk(WebElement element){
	        Actions actions=new Actions(driver);
	        actions.moveToElement(element).build().perform();
	    }

	    public  void jsClick(WebElement element) {
	        getJSExecutor().executeScript("arguments[0].click()", element);
	    }

	    public JavascriptExecutor getJSExecutor(){
	        return ((JavascriptExecutor)driver);
	    }

}
