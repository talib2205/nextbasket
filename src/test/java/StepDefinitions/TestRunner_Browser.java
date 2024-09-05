package StepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@io.cucumber.testng.CucumberOptions(
		features="src/test/resources/Features",
		glue= {"StepDefinitions"},
		tags = "@TC_Users_Search_List_01_01",
		monochrome=true,
		plugin= {"pretty",
				"html:target/cucumber-html-report.html",
				"json:target/cucumber-json-report.json",
				"junit:target/cucumber-junit-xml-report.xml",
				"rerun:target/cucumber-reports/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
		dryRun = false
		)

public class TestRunner_Browser extends AbstractTestNGCucumberTests {
    public TestRunner_Browser() {
        RunnerInfo.setName("browser");
        RunnerInfo.setURL("url_uat");
    }

//		@Override
//		@DataProvider(parallel = true)
//		public Object[][] scenarios()
//		{
//			return super.scenarios();
//		}


}