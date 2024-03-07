
package HRM_TestNG_EndToEnd;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HRM_EndToEnd_TestNG {
	
	WebDriver endToEnd;
	
	@Parameters({"browser"})
	
  @Test(priority=1)
  public void setup(String brows) throws InterruptedException {
		if(brows.equals("chrome")) {
			  WebDriverManager.chromedriver().setup();
			  endToEnd = new ChromeDriver();
		}
		else if(brows.equals("edge")) {
			  WebDriverManager.edgedriver().setup();
			  endToEnd = new EdgeDriver();
		}

	  endToEnd.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  endToEnd.get("https://opensource-demo.orangehrmlive.com/");
	  //endToEnd.manage().window().maximize();  
	  
	  Thread.sleep(3000);
  }
  
  
  @Test(priority=2, dependsOnMethods= {"setup"})
  public void logon() {
		//Step-4: Insert UserName
	  endToEnd.findElement(By.name("username")).sendKeys("Admin");
	  endToEnd.findElement(By.name("password")).sendKeys("admin123");
	  endToEnd.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  String expected_user = "55555AnushaAnushaAnushaAnusha Sivanandan";
	  String actual_user = endToEnd.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText();
	  
	  Assert.assertEquals(actual_user, expected_user);
  }
  
  
  @Test(priority=3, dependsOnMethods= {"logon"})
  public void register() throws InterruptedException {
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a")).click();
	  endToEnd.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
	  
	  Thread.sleep(3000);
		
	  endToEnd.findElement(By.name("firstName")).sendKeys("Harry");
	  endToEnd.findElement(By.name("middleName")).sendKeys("John");
	  endToEnd.findElement(By.name("lastName")).sendKeys("Smith");
		
	  Thread.sleep(2000);
		
	  endToEnd.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input")).sendKeys("HJS001");
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input")).sendKeys("HjS_001");
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input")).sendKeys("HjS_001");
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")).click();
  }
  
  
  @Test(priority=4, dependsOnMethods= {"logon"})
  public void PersonalDetails() throws InterruptedException {
	  
	  endToEnd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	  //Thread.sleep(3000);
		
	  //Step 14: Nickname
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div[2]/div/div/div[2]/input")).sendKeys("HJSmith");
	  //Step 15: OtherID
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input")).sendKeys("0260");
	  //Step 16: Driver's License
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[1]/div/div[2]/input")).sendKeys("1230587946");
		
	  //Step 17: License Expiry Date	
	  String month = "July";
	  String year = "2030";
	  String day = "31";
		
	  //Select Drop Down
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/i")).click();
		
	  //Select month & year
	  while(true) {
		  String mnth = endToEnd.findElement(By.xpath("//li[@class='oxd-calendar-selector-month']//p")).getText();
		  String yr = endToEnd.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']//p")).getText();
			
	  if(mnth.equals(month) && yr.equals(year)) {
		  break;
	  	}
	  	 endToEnd.findElement(By.xpath("//i[@class='oxd-icon bi-chevron-right']")).click(); //Foward Date search
	  }
		
	  //Select date
	  java.util.List<WebElement> allDates = endToEnd.findElements(By.xpath("//div[@class='oxd-calendar-date']"));
	  
	  for(WebElement dt:allDates) {
		  if(dt.getText().equals(day)) {
			  dt.click();
			  break;
		  }
	  }
		
	  
	  //SSN Number
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[3]/div[1]/div/div[2]/input")).sendKeys("12305");
		
	  //SIN Number
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[3]/div[2]/div/div[2]/input")).sendKeys("85279");
		
	  //Nationality DropDown
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[2]/i")).click();
	  java.util.List<WebElement> nation = endToEnd.findElements(By.xpath("//div[@role='listbox']//span"));
		
	  System.out.println("Total number of options: " + nation.size());
		
	  for(WebElement option:nation) {
		  String text = option.getText();
			
		  if(text.equals("Brazilian")) {
			  option.click();
			  break;
		  }
	  }
		
	  //Marital Status DropDown
	  endToEnd.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[2]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")).click();
	  java.util.List<WebElement> status = endToEnd.findElements(By.xpath("//div[@role='listbox']//span"));
		
	  for(WebElement statusOption:status) {
		  String statusText = statusOption.getText();
			
		  if(statusText.equals("Married")) {
			  statusOption.click();
			  break;
		  }
	  }
		
	  
	  //Date of Birth
	  String bMonth = "August";
	  String bYear = "2018";
	  String bDay = "15";
		
	  //Select Drop Down
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div/i")).click();
		
	  //Select month & year
	  while(true) {
		  String bMnth = endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li[1]/div/p")).getText();
		  String bYr = endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li[2]/div/p")).getText();
			
		  if(bMnth.equals(bMonth) && bYr.equals(bYear)) {
			  break;
		  }
			
		  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/button[1]/i")).click(); //Back Date search
	  }
		
	  //Select date
	  java.util.List<WebElement> bDates = endToEnd.findElements(By.xpath("//div[@class='oxd-calendar-date']"));
	  for(WebElement bDt:bDates) {
		  if(bDt.getText().equals(bDay)) {
			  bDt.click();
			  break;
		  }	
	  }
	  
		
	  //Gender Radio Button
	  //endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/label/span")).click();
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/label/span")).click();
		
	  //Military Service
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/div/div[1]/div/div[2]/input")).sendKeys("No");
		
	  //Smoker Checkbox
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/div/div[2]/div/div[2]/div/label/span/i")).click();
		
		//Save Button 1
		//endToEnd.findElement(By.xpath("//div[@class='oxd-form-actions']//button")).click();
		
	  Thread.sleep(2000);
		
	  //Blood Type DropDown
	  endToEnd.findElement(By.xpath("//div[@class='orangehrm-custom-fields']//div[@class='orangehrm-card-container']//form[@class='oxd-form']//div[@class='oxd-form-row']//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
	  java.util.List<WebElement> bloodType = endToEnd.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[3]/div[1]/div"));
		
	  for(WebElement typeOption:bloodType) {
		  String bldType = typeOption.getText();
			
		  if(bldType.equals("B-")) {
			  typeOption.click();
			  break;
		  }
	  }
		
	  
	  //Save Button 2
	  endToEnd.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[2]/button")).click();

	  Thread.sleep(2000);
  }
  
  
  
  @Test(priority=5, dependsOnMethods= {"logon","PersonalDetails"})
  public void logoff() {
	  
	  endToEnd.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
	  java.util.List<WebElement> logoff = endToEnd.findElements(By.linkText("Logout"));
	  
	  for(WebElement log_off:logoff) {
		  String off = log_off.getText();
			
		  if(off.equals("Logout")) {
			  log_off.click();
			  break;
		  }
	  }
  }
  
  
  @Test(priority=6, dependsOnMethods= {"logoff"})
  public void logonAgain() throws InterruptedException {
	  
	  endToEnd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	  Thread.sleep(3000);
	  
	  endToEnd.findElement(By.name("username")).sendKeys("HJS001");
	  endToEnd.findElement(By.name("password")).sendKeys("HjS_001");
	  endToEnd.findElement(By.xpath("//button[@type='submit']")).click();
	  
	  String newUser = "Harry Smith";
	  String newActual_user = endToEnd.findElement(By.xpath("//h6[contains(.,'Harry Smith')]")).getText();
	  
	  Assert.assertEquals(newActual_user, newUser);
  }
  
  
  
  @Test(priority=7, dependsOnMethods= {"logonAgain"})
  public void signOff() throws InterruptedException {
	  
	  Thread.sleep(3000);
	  
	  endToEnd.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
	  java.util.List<WebElement> logoff2 = endToEnd.findElements(By.linkText("Logout"));
		
	  for(WebElement log_off2:logoff2) {
		  String off2 = log_off2.getText();
			
		  if(off2.equals("Logout")) {
			  log_off2.click();
			  break;
		  }
	  }
	  
		String done = "Login";
		String actual_done = endToEnd.findElement(By.xpath("//h5[normalize-space()='Login']")).getText();
		
		Assert.assertEquals(actual_done, done);
  }
  
}
