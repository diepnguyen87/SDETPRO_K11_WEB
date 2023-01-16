package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends BaseItemDetailsComponent {

    private final static By allOptionsSel = By.cssSelector(".option-list input");
    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType (String type);

    public String selectHDDType(String type){
        return selectCompOption(type);
    }
    public String selectOSType(String type){
        return selectCompOption(type);
    }
    protected String selectCompOption(String type){
        String selectorStr = "//label[contains(text(), '" + type + "')]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElem = null;

        try{
            optionElem = findElement(optionSel);
        }catch (Exception ignored){}

        if(optionElem != null){
            optionElem.click();
            return optionElem.getText();
        }else{
            throw new RuntimeException("[ERR] The option: " + type + " is not existing to select!");
        }
    }

    public void unSelectDefaultOptions(){
        List<WebElement> allOptionsElem = component.findElements(allOptionsSel);
        allOptionsElem.forEach(option -> {
            if(option.getAttribute("checked") !=null){
                option.click();
            }
        });
    }
}
