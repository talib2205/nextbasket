package DriverManager;


import StepDefinitions.RunnerInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Driver {
    public Driver() { // private constructor
    }
    public static Map<String, Object> driver = new HashMap<>();
    public String status = "android";
    public static Map<String,List<AppiumDriverLocalService>> appiumDriverLocalServices = new HashMap<>();

    public  void setStatus(String newStatus) {
        status = newStatus;
    }
    public  void setUp(String environment) {
        try {
            setStatus(environment);
            switch (environment.toLowerCase()) {
                case "windows" -> openWindowsDriver();
                case "browser" -> openNativeBrowser();
                default -> {
                    driver = null;
                    System.out.println("No driver found in switch method from json file");
                }
            }
        } catch (Exception e) {
            System.out.println("Source: " + e.getCause());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Stack Trace: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private  void openWindowsDriver() {
        System.out.println("Starting Windows Appium Service");
        DesiredCapabilities capabilities = readCapabilities("./src/test/resources/data/windows.properties");
        System.out.println("Appium Started");
        System.out.println("Windows Driver: " + driver);
    }

    private  void openNativeBrowser() {
        System.out.println("Starting Browser Driver Service");
        String browserName = getBrowserName();
        System.out.println("Starting Browser Driver Service:" + browserName);
        switch (browserName.toLowerCase()) {
            case "chrome" -> setupChromeBrowser();
            case "firefox" -> setupFirefoxBrowser();
            case "edge" -> setupEdgeBrowser();
            case "safari" -> setupSafariBrowser();
        }
        System.out.println("Initiated Browser Driver: " + driver);
    }

    private  void setupChromeBrowser() {
        System.out.println("inside setup chrome browser 1");
        WebDriverManager.chromedriver().setup();
        System.out.println("inside setup chrome browser 2");
        ChromeOptions option = new ChromeOptions();
        System.out.println("inside setup chrome browser 3");
        option.addArguments("--remote-allow-origins=*");
        System.out.println("inside setup chrome browser 4");
        option.setCapability(ChromeOptions.CAPABILITY, option);
        System.out.println("inside setup chrome browser 5");
        driver.put(status,new ChromeDriver(option));
        System.out.println("inside setup chrome browser 6");
    }

    private  void setupFirefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver.put(status,new FirefoxDriver());
    }

    private  void setupSafariBrowser() {
        WebDriverManager.safaridriver().setup();
        driver.put(status, new SafariDriver());
    }

    private  void setupEdgeBrowser() {
        WebDriverManager.edgedriver().setup();
        driver.put(status,new EdgeDriver());
    }

    private  DesiredCapabilities readCapabilities(String path) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            Enumeration<?> e = prop.propertyNames();

            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                capabilities.setCapability(key, prop.getProperty(key));
            }
            return capabilities;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  String getBrowserName() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("./src/test/resources/data/browserConfig.properties"));
            return prop.getProperty("browser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void stopBrowserDriver() {
        var browserName = getBrowserName();
        switch (browserName.toLowerCase()) {
            case "chrome" -> {
                var chromeDriver = (ChromeDriver) driver.get("browser");
                chromeDriver.quit();
            }
            case "firefox" -> {
                var foreFoxDriver = (FirefoxDriver) driver.get("browser");
                foreFoxDriver.quit();
            }
            case "edge" -> {
                var edgeDriver = (EdgeDriver) driver.get("browser");
                edgeDriver.quit();
            }
        }
    }
}
