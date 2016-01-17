import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class Launch {
	
	public static boolean loadAndFind(List<String> linksClicked, WebDriver driver){

		driver.navigate().to("http://www.bing.com/news?FORM=NWRFSH");
		
		try {
			System.out.println("will wait 2 sec as just loaded bing");
			Thread.sleep(2000);
			System.out.println("done wait 2sec");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> links = driver.findElements(By.xpath("//a[@class='linkcover']"));
		
		for(WebElement link : links){
			String url = link.getAttribute("href");
			url = url.substring(0, url.indexOf("&filter"));
			if(!linksClicked.contains(url)){
				//link.click();
				driver.navigate().to(link.getAttribute("href"));
				linksClicked.add(url);
				return true;
			}
		}

		return false;
	} 
	
	public static void main(String[] args){
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile p = profile.getProfile("default");
		WebDriver driver = new FirefoxDriver(p);
		driver.get("data:text/html,");
		List<String> linksClicked = new ArrayList<String>();
		
		while(loadAndFind(linksClicked, driver)){
			try {
				System.out.println("a loadAndFind finised, so now will wait 2sec");
				Thread.sleep(2000);
				System.out.println("done 2sec for loadAndFind");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.quit();
	}
}
