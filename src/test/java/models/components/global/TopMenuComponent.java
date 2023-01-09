package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainMenuComponent> getMainMenuComponentList(){
        return findComponents(MainMenuComponent.class, driver);
    }
    @ComponentCssSelector(value = ".top-menu > li")
    public static class MainMenuComponent extends Component {

        public WebElement link(){
            return findElement(By.tagName("a"));
        }
        public MainMenuComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public List<SubMenuComponent> getSubMenuComponentList(){
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(SubMenuComponent.class, driver);
        }
    }

    @ComponentCssSelector(value = ".top-menu > li .sublist")
    public static class SubMenuComponent extends Component {
        public SubMenuComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }
}
