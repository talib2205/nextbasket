package StepDefinitions;

import DriverManager.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


public class Hooks {
    private Scenario scenario;
    RunnerInfo runnerInfo;

    Driver driver;

    @After("@browser")
    public void tearDownBrowser(Scenario scenario) {
//        ((AppiumDriver) driver).quit();
        String name = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceType");
//
//        String name  = RunnerInfo.getName();
        ((WebDriver) Driver.driver.get(name)).quit();
      }


    @AfterMethod
    @AfterStep
    public void TakeScreenshotFailure(Scenario scenario) throws FileNotFoundException {
        if (scenario.isFailed() || String.valueOf(scenario.getStatus()).contains("undefined")) {
            TakesScreenshot ts = (TakesScreenshot) Driver.driver.get(RunnerInfo.getDeviceType());
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
    }
    protected String getTag(List<String> tagsNames) {
        return tagsNames.stream()
                .filter(filter -> filter.startsWith("@tag:"))
                .map(filter -> filter.substring(5)).collect(Collectors.joining());
    }
}
