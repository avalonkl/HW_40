package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.*;
import java.util.logging.*;

public class Chrome {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        String driverPath = "";

        String url = "http://facebook.com/";
        String email_address = "avalonkl@gmail.com";
        String password = "*********";

        if (System.getProperty("os.name").toUpperCase().contains("MAC"))
            driverPath = "./resources/webdrivers/mac/chromedriver";
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
            driverPath = "./resources/webdrivers/pc/chromedriver.exe";
        else throw new IllegalArgumentException("Unknown OS");

        System.setProperty("webdriver.chrome.driver", driverPath);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("disable-infobars");
        option.addArguments("--disable-notifications");
        if (System.getProperty("os.name").toUpperCase().contains("MAC"))
            option.addArguments("-start-fullscreen");
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
            option.addArguments("--start-maximized");
        else throw new IllegalArgumentException("Unknown OS");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.get(url);


        String title = driver.getTitle();
        String copyright = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div[3]/div/span"))).getText();
        System.out.println("Size of Copyright: " + (Dimension) driver.findElement(By.xpath("//div[2]/div/div[3]/div/span")).getSize());
        System.out.println("Location of Copyright: " + (Point) driver.findElement(By.xpath("//div[2]/div/div[3]/div/span")).getLocation());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_address);
        System.out.println("Size of Email: " + (Dimension) driver.findElement(By.id("email")).getSize());
        System.out.println("Location of Email: " + (Point) driver.findElement(By.id("email")).getLocation());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
        System.out.println("Size of Password: " + (Dimension) driver.findElement(By.id("pass")).getSize());
        System.out.println("Location of Password: " + (Point) driver.findElement(By.id("pass")).getLocation());

        wait.until(ExpectedConditions.elementToBeClickable(By.id("u_0_5"))).click();
        System.out.println("Size of Login Button: " + (Dimension) driver.findElement(By.id("u_0_5")).getSize());
        System.out.println("Location of Login Button: " + (Point) driver.findElement(By.id("u_0_5")).getLocation());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span"))).click();
        System.out.println("Size of Timeline Button: " + (Dimension) driver.findElement(By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span")).getSize());
        System.out.println("Location of Timeline Button: " + (Point) driver.findElement(By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span")).getLocation());

        String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a"))).getText();
        System.out.println("Size of Friends Button: " + (Dimension) driver.findElement(By.xpath("//div[2]/ul/li[3]/a")).getSize());
        System.out.println("Location of Friends Button: " + (Point) driver.findElement(By.xpath("//div[2]/ul/li[3]/a")).getLocation());

        wait.until(ExpectedConditions.elementToBeClickable(By.id("userNavigationLabel"))).click();
        System.out.println("Size of Account Setting Button: " + (Dimension) driver.findElement(By.id("userNavigationLabel")).getSize());
        System.out.println("Location of Account Setting Button: " + (Point) driver.findElement(By.id("userNavigationLabel")).getLocation());

        System.out.println("Size of Log Out Button: " + (Dimension) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[14]/a/span/span"))).getSize());
        System.out.println("Location of Log Out Button: " + (Point) driver.findElement(By.xpath("//li[14]/a/span/span")).getLocation());
        driver.findElement(By.xpath("//li[14]/a/span/span")).click();


        driver.quit();

        char[] chars = friends.toCharArray();
        friends = "";
        for (int i = 0; i < chars.length; i++){
            if ((int) chars[i] > 47 && (int) chars[i] < 58){
                friends += chars[i];
            }
        }

        System.out.println("Browser is: Chrome");
        System.out.println("Title of the page: " + title);
        System.out.println("Copyright: " + copyright);
        System.out.println("You have " + friends + " friends");
    }
}