package StepDefinitions;

import org.testng.Reporter;

public class RunnerInfo {
    private static String name,type;
    private static String url;

    public static void setName(String value) {
        name = value;

    }

    public static void setType(String value) {
        type = value;

    }

    public static String getName() {
        return name;
    }

    public static void setURL(String value) {
        url = value;

    }

    public static String getURL() {
        return url;
    }
    public static String getDeviceType() {
        return  Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceType");
    }

    public static String getDeviceName() {
        return  Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName");
    }

}
