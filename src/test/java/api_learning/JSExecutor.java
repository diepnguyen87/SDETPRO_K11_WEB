package api_learning;

import org.openqa.selenium.JavascriptExecutor;
import tests.BaseTest;
import url.Urls;

public class JSExecutor extends BaseTest implements Urls {
    public static void main(String[] args) {
        try{
            driver.get(baseUrl.concat(floatingMenuSlug));
            Thread.sleep(5000);
            JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
            jsExcutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(5000);
            jsExcutor.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
