package tests;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeTest
    public void initBrowserSession(){
        driver = DriverFactory.getDriver();
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession(){
        if(driver != null){
            driver.quit();
        }
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result){
        //Capture screenshot with name MethodName-y-m-d-h-m-s.png
        if(result.getStatus() == ITestResult.FAILURE){
            //1. Get Method Name
            String methodName = result.getName();

            //2. Get date time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH + 1);
            int d = calendar.get(Calendar.DATE);
            int h = calendar.get(Calendar.HOUR);
            int mins = calendar.get(Calendar.MINUTE);
            int s = calendar.get(Calendar.SECOND);

            //3. Screenshot name
            String screenshotName = methodName + "-" + y + "-" + m + "-" + d + "-" + h + "-" + mins + "-" + s + ".png";

            //4. Take screenshot
            File screenshotBase64Data = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            //5. Save to local
            try{
                String screenshotLocation = System.getProperty("user.dir") + "/screenshots" + screenshotName;
                FileUtils.copyFile(screenshotBase64Data, new File(screenshotLocation));

                //6. Attach to allure report
                Path path = Paths.get(screenshotLocation);
                try(InputStream inputStream = Files.newInputStream(path)) {
                    Allure.addAttachment(methodName, inputStream);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
