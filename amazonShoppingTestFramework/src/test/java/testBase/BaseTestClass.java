package testBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public class BaseTestClass {

    protected AndroidDriver driver;
    private static AppiumDriverLocalService service;

    @BeforeMethod
    public void setup() {

        // Prepare Appium session and initialize driver
        File appLocation = new File("app");
        File app = new File(appLocation, "Amazon_shopping.apk");
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        dc.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        dc.setCapability("app", app.getAbsolutePath());

        driver = new AndroidDriver(service.getUrl(), dc);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
