package resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class base {
    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException{
        Logger log = LogManager.getLogger(base.class.getName());

        //chrome
        //firefox
        prop = new Properties();
        FileInputStream  fileInputStream = new FileInputStream("src/main/java/resources/data.properties");
        prop.load(fileInputStream);
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver");
            log.info("Browser is initialized = " + browserName);
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "src/main/driver/geckodriver");
            log.info("Browser is initialized = " + browserName);
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }
}
