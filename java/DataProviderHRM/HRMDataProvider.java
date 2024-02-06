package DataProviderHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HRMDataProvider {
	
	WebDriver dataProvider;
	@Parameters({"logonBrowser"})
	
  @Test(priority=1)
  public void setup(String parallel_browsers) throws InterruptedException {
		
		if (parallel_browsers.equals("chrome")) {
			  WebDriverManager.chromedriver().setup();
			  dataProvider = new ChromeDriver();
		}
		else if(parallel_browsers.equals("edge")){
			  WebDriverManager.edgedriver().setup();
			  dataProvider = new EdgeDriver();
		}

	  
	  dataProvider.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  dataProvider.get("https://opensource-demo.orangehrmlive.com/");
	  //dataProvider.manage().window().maximize();
	  
	  Thread.sleep(3000);
  }
  
  
  @Test(priority=2, dataProvider="credentials")
  public void logon(String user, String password) {
	  dataProvider.findElement(By.name("username")).sendKeys(user);
	  dataProvider.findElement(By.name("password")).sendKeys(password);
	  dataProvider.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  String expected_user = "Paul1 Collings";
	  String actual_user = dataProvider.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText();
	  
	  Assert.assertEquals(actual_user, expected_user);
  }
  
  
  @Test(priority=3)
  public void logoff() throws InterruptedException {
	  Thread.sleep(3000);
	  
	  dataProvider.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
	  java.util.List<WebElement> logoff2 = dataProvider.findElements(By.linkText("Logout"));
		
	  for(WebElement log_off2:logoff2) {
		  String off2 = log_off2.getText();
			
		  if(off2.equals("Logout")) {
			  log_off2.click();
			  break;
		  }
	  }
	  
		String done = "Login";
		String actual_done = dataProvider.findElement(By.xpath("//h5[normalize-space()='Login']")).getText();
		
		Assert.assertEquals(actual_done, done);
  }
  
  
  @DataProvider(name="credentials")
  String [][] logindata(){
	  String data [][]= {
			  {"Admin", "admin246"},
			  {"Admin ", " admin123"},
			  {"Admin", "Admin123"},
			  {"admin", "admin123"},
			  {"Admin", "admin123"}
			  };
			return data;
  		}
  
}