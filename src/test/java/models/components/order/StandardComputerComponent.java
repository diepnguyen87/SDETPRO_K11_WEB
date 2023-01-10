package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {

    private static final By productAttributeSel = By.cssSelector("select[id^='product_attribute']");

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropDownElem = findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropDownElem, type);
    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropDownElem = findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropDownElem, type);
    }

    private String selectOption(WebElement dropdownList, String type){
        Select select = new Select(dropdownList);
        List<WebElement> optionList = select.getOptions();
        String fullOption = null;
        for (WebElement option : optionList) {
            String currentOption = option.getText();
            String currentOptionWithoutSpaces = currentOption.trim().replace(" ", "");
            if(currentOptionWithoutSpaces.startsWith(type)){
                fullOption = currentOption;
                break;
            }
        }
        if(fullOption == null){
            throw new RuntimeException("[ERR] The option " + type + " is not existing to select");
        }
        select.selectByVisibleText(fullOption);
        return fullOption;
    }

}
