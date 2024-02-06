package HRMAddEmployee;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddEmployee {

	public static void main(String[] args) throws InterruptedException {
		
		//-------------------------------LogOn Page-------------------------------
		
		//Step-1: Setup and Open Browser
		WebDriverManager.chromedriver().setup();
		WebDriver add = new ChromeDriver();
		
		//Step-2: Manage delayed response
		add.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step-3: Open URL & Maximize Page
		add.get("https://opensource-demo.orangehrmlive.com/");
		add.manage().window().maximize();
		
		Thread.sleep(3000);
		
		//Step-4: Insert UserName
		add.findElement(By.name("username")).sendKeys("Admin");
		
		//Step-5: Insert Password
		add.findElement(By.name("password")).sendKeys("admin123");
		
		//Step-6: Click on the Login Button
		add.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		//Step-7: Verify Successful Login
		String user = "";
		String actual_user = "Admin Admin";		//It changes constantly so you'll need to update.
		
		user = add.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText();
		
		if(user.equals(actual_user)) {
			System.out.println("Logon Automation Successful!");
		}
		else {
			System.out.println("Logon Automation Failed.");
		}
		
		//Step-8: Click on PIM
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a")).click();
		
		//Step-9: Click on Add Employee
		add.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
		
		//Step-10: Verify Successful Login
		String heading = "";
		String actual_heading = "Add Employee";
		
		heading = add.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")).getText();
		
		if(heading.equals(actual_heading)) {
			System.out.println("On the Add Employee section.");
		}
		else {
			System.out.println("Couldn't get to the section.");
		}
		
		//-------------------------------Add Employee-------------------------------
		
		Thread.sleep(3000);
		
		//Step-11: Add Details
		add.findElement(By.name("firstName")).sendKeys("Harry");
		add.findElement(By.name("middleName")).sendKeys("John");
		add.findElement(By.name("lastName")).sendKeys("Smith");
		
		
		//Step-12: Create Login Details for New User
		Thread.sleep(2000);
		
		//Radio Button
		add.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
		
		//username
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input")).sendKeys("HJS001");
		
		//Password
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input")).sendKeys("HjS_001");
		//Confirm password
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input")).sendKeys("HjS_001");
		
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")).click();
		
		
		//-------------------------------Personal Details-------------------------------
		
//		WebElement frame1 = add.findElement(By.cssSelector("div.orangehrm-edit-employee-navigation"));
//		add.switchTo().frame(frame1);
		
		//Step-13: Verify Successful Registration
		String personalD = "";
		String actual_personalD = "Harry";
		
		
		personalD = add.findElement(By.name("firstName")).getText();
		//personalD = add.findElement(By.name("firstName")).getAttribute(actual_personalD);
		//personalD = add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[1]/div[1]/h6")).getText();
		
		if(personalD.equals(actual_personalD)) {
			System.out.println("Employee Registered Successfully! Please complete personanl details.");
		}
		else {
			System.out.println("Employee not Registered.");
		}

		Thread.sleep(3000);
		
		//Step 14: Nickname
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div[2]/div/div/div[2]/input")).sendKeys("HJSmith");
		//Step 15: OtherID
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input")).sendKeys("0260");
		//Step 16: Driver's License
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[1]/div/div[2]/input")).sendKeys("1230587946");
		
		//Step 17: License Expiry Date	
				
		String month = "July";
		String year = "2030";
		String day = "31";
		
		//Select Drop Down
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/i")).click();
		
		//Select month & year
		while(true) {
			String mnth = add.findElement(By.xpath("//li[@class='oxd-calendar-selector-month']//p")).getText();
			String yr = add.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']//p")).getText();
			
			if(mnth.equals(month) && yr.equals(year)) {
				break;
			}
			
			add.findElement(By.xpath("//i[@class='oxd-icon bi-chevron-right']")).click(); //Foward Date search
		}
		
		//Select date
		java.util.List<WebElement> allDates = add.findElements(By.xpath("//div[@class='oxd-calendar-date']"));
		for(WebElement dt:allDates) {
			if(dt.getText().equals(day)) {
				dt.click();
				break;
			}
			
		}
		
		
		//SSN Number
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[3]/div[1]/div/div[2]/input")).sendKeys("12305");
		
		//SIN Number
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[3]/div[2]/div/div[2]/input")).sendKeys("85279");
		
		//Nationality DropDown
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[2]/i")).click();
		java.util.List<WebElement> nation = add.findElements(By.xpath("//div[@role='listbox']//span"));
		
		System.out.println("Total number of options: " + nation.size());
		
		for(WebElement option:nation) {
			String text = option.getText();
			
			if(text.equals("Brazilian")) {
				option.click();
				break;
			}
		}
		
		//Marital Status DropDown
		add.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//div[2]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")).click();
		java.util.List<WebElement> status = add.findElements(By.xpath("//div[@role='listbox']//span"));
		
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
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div/i")).click();
		
		//Select month & year
		while(true) {
			String bMnth = add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li[1]/div/p")).getText();
			String bYr = add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li[2]/div/p")).getText();
			
			if(bMnth.equals(bMonth) && bYr.equals(bYear)) {
				break;
			}
			
			add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/button[1]/i")).click(); //Back Date search
		}
		
		//Select date
		java.util.List<WebElement> bDates = add.findElements(By.xpath("//div[@class='oxd-calendar-date']"));
		for(WebElement bDt:bDates) {
			if(bDt.getText().equals(bDay)) {
				bDt.click();
				break;
			}
			
		}
		
		
		//Gender Radio Button
		//add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/label/span")).click();
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/label/span")).click();
		
		//Military Service
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/div/div[1]/div/div[2]/input")).sendKeys("No");
		
		//Smoker Checkbox
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/div/div[2]/div/div[2]/div/label/span/i")).click();
		
		//Save Button 1
		//add.findElement(By.xpath("//div[@class='oxd-form-actions']//button")).click();
		
		Thread.sleep(2000);
		
		//Blood Type DropDown
		add.findElement(By.xpath("//div[@class='orangehrm-custom-fields']//div[@class='orangehrm-card-container']//form[@class='oxd-form']//div[@class='oxd-form-row']//div[@class='oxd-grid-3 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
		java.util.List<WebElement> bloodType = add.findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[3]/div[1]/div"));
		
		for(WebElement typeOption:bloodType) {
			String bldType = typeOption.getText();
			
			if(bldType.equals("B-")) {
				typeOption.click();
				break;
			}
		}
		
		
		//Save Button 2
		add.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/form/div[2]/button")).click();

		Thread.sleep(2000);
		
		//-------------------------------Log-off-------------------------------

		add.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		java.util.List<WebElement> logoff = add.findElements(By.linkText("Logout"));
		
		for(WebElement log_off:logoff) {
			String off = log_off.getText();
			
			if(off.equals("Logout")) {
				log_off.click();
				break;
			}
		}
		
		Thread.sleep(3000);

		//-------------------------------Logon Again-------------------------------

		//Step-4: Insert UserName
		add.findElement(By.name("username")).sendKeys("HJS001");
		
		//Step-5: Insert Password
		add.findElement(By.name("password")).sendKeys("HjS_001");
		
		//Step-6: Click on the Login Button
		add.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		//Step-7: Verify Successful Login
		String newUser = "";
		String newActual_user = "Harry Smith";
		
		newUser = add.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText();
		//newUser = add.findElement(By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']")).getAttribute(newActual_user);
		
		if(newUser.equals(newActual_user)) {
			System.out.println("New User Logon Automation Successful!");
		}
		else {
			System.out.println("Logon Automation Failed.");
		}
		
		Thread.sleep(3000);
		
		add.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		java.util.List<WebElement> logoff2 = add.findElements(By.linkText("Logout"));
		
		for(WebElement log_off2:logoff2) {
			String off2 = log_off2.getText();
			
			if(off2.equals("Logout")) {
				log_off2.click();
				break;
			}
		}
		
		Thread.sleep(3000);
		
		//Final logoff validation
		String done = "";
		String actual_done = "Login";
		
		done = add.findElement(By.xpath("//h5[normalize-space()='Login']")).getText();
		
		if(done.equals(actual_done)) {
			System.out.println("Logoff Successful! End-End Automation Done!");
		}
		else {
			System.out.println("End-End Automation Failed.");
		}
				
	}

}
