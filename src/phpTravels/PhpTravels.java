package phpTravels;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PhpTravels {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"\\C:\\\\Users\\\\Nurkulov\\\\Documents\\\\selenuim dependencies\\\\drivers\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

	@Test
	public void TestCase() throws InterruptedException {
		driver.get("https://phptravels.com/demo/");
		driver.findElement(By.partialLinkText("PHPTRAVELS")).click();
		List<String> list = new ArrayList(driver.getWindowHandles());
		String originalWindow = list.get(0);
		System.out.println(originalWindow);
		System.out.println(list.get(1));
		String secWindow = "";
		for (int i = 0; i < list.size(); i++) {

			if (!list.get(i).equals(originalWindow)) {
				secWindow += list.get(i);
			}
		}
		driver.switchTo().window(secWindow);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[@class='row']//li[@id='li_myaccount']")).click();
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(5000);
		// WebDriverWait wait=new WebDriverWait(driver,15);
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demouser" + Keys.ENTER);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//div[@class='row'][12]//a[@class='btn btn-action btn-block']")).click();

		driver.switchTo().window(secWindow);
		driver.close();

		Thread.sleep(3000);

		List<String> list1 = new ArrayList(driver.getWindowHandles());
		String thirdWindow = "";
		for (int i = 0; i < list1.size(); i++) {

			if (!list1.get(i).equals(originalWindow)) {
				thirdWindow += list1.get(i);
			}
		}

		System.out.println(secWindow + " this is second window");
		System.out.println(thirdWindow + " this is third window");

		// swhitching back and close it
		driver.switchTo().window(thirdWindow);
		// driver.switchTo().window(thirdWindow);
		
		//Invoice info
		String hotelName = driver.findElement(By.xpath("//table//tr[4]//table//tbody//tr[2]//tbody/tr//td")).getText();
		String expHotelName = "Jumeirah Beach Hotel";
		Assert.assertEquals(hotelName, expHotelName);
		WebElement table = driver.findElement(By.cssSelector("table.table.table-bordered"));
		String deposit = table.findElement(By.xpath("//tbody//td[1]")).getText();
		String tax = table.findElement(By.xpath("//tbody//td[2]")).getText();
		String total = table.findElement(By.xpath("//tbody//td[3]")).getText();
		String bookingCode=driver.findElement(By.xpath("//table//table//tr//div[5]")).getText();
		bookingCode=bookingCode.substring(13);
		//going to another site and login
		driver.get("https://phptravels.com/demo/");
		Thread.sleep(3000);
		driver.findElement(By.linkText("HTTP://WWW.PHPTRAVELS.NET/ADMIN")).click();
		List<String> list2 = new ArrayList(driver.getWindowHandles());
		String forthWindow = "";
		for (int i = 0; i < list2.size(); i++) {

			if (!list2.get(i).equals(originalWindow)&&!list2.get(i).equals(thirdWindow)) {
				forthWindow += list2.get(i);
			}
		}
		driver.switchTo().window(forthWindow);
		
	
	
	
//	@Test (priority=1)
//	public void testCase1() throws InterruptedException {
		driver.get("https://www.phptravels.net/admin");
		driver.findElement(By.name("email")).sendKeys("admin@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demoadmin");
		driver.findElement(By.xpath("//span[.='Login']")).click();
//		String bookingCode="5162";
		WebElement tableInfo=driver.findElement(By.cssSelector("table.xcrud-list"));
		List<WebElement> firstRow=driver.findElements(By.xpath("//table[1]//td[4]"));
		WebElement neededRow;
	
//		
//		for(int i=0;i<firstRow.size();i++) {
//			if(firstRow.get(i).getText().equals(bookingCode)) {
//				neededRow=driver.findElement(By.xpath("//table[1]//tr["+i+"]//td[12]//a[2]"));
////				neededRow.click();
//			}else {
					driver.findElement(By.xpath("//ul[@class='pagination']//a[.='2']")).click();
					Thread.sleep(3000);
					List<WebElement> firstRow1=driver.findElements(By.xpath("//table[1]//td[4]"));
					for(WebElement pr:firstRow1) {
						
						System.out.println(pr.getText());
					}
					
					for(int bi=0;bi<firstRow1.size();bi++) {
					if(firstRow1.get(bi).getText().equals(bookingCode)) {
						WebElement neededRow1=driver.findElement(By.xpath("//table[1]//tr["+(bi+1)+"]//td[12]//a[2]"));
						neededRow1.click(); 
					}
				
				}
//			}
//		}
		String expDep=driver.findElement(By.xpath("//table[@class='table table-bordered']//td[@id='topaytotal']")).getText();
		String expTax =driver.findElement(By.xpath("//table[@class='table table-bordered']//td[@id='displaytax']")).getText();
		String expTotal=driver.findElement(By.xpath("//table[@class='table table-bordered']//td[@id='grandtotal']")).getText();
//					Assert.assertEquals(deposit, expDep);
//					Assert.assertEquals(tax, expTax);
//					Assert.assertEquals(total, expTotal);
		
		
		
	}
}
