package engine;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.BeforeSuite;

public class Driver {

    public static AndroidDriver driver;

    @BeforeSuite
    public AndroidDriver settingDriver() throws MalformedURLException {
        System.out.println("appium test");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName","Android");
        //this step recently added to tell that elements shud be accessed with uiautomator
        cap.setCapability("appium:automationName","UiAutomator2");
        cap.setCapability("appium:deviceName","Pixel 2");
        cap.setCapability("appium:udid","emulator-5554");
        cap.setCapability("appium:platformVersion","11.0");
        cap.setCapability("appium:newCommandTimeout","3000");
        cap.setCapability("appium:app","/Users/seema/Downloads/Zip/Android-MyDemoApp.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),cap);
        return driver;
    }
}
