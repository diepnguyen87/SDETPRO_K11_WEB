package support.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectEx extends Select {

    public final String OPTION_1 = "Option 1";

    public SelectEx(WebElement element) {
        super(element);
    }

    public void selectFirstOption(){
        selectByVisibleText(OPTION_1);
    }
}
