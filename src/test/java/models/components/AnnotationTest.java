package models.components;

public class AnnotationTest {

    public <T> void getComponentCssSelector(Class<T> tClass){
        try{
            String cssSelector = tClass.getAnnotation(ComponentCssSelector.class).value();
            System.out.println(cssSelector);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new AnnotationTest().getComponentCssSelector(LoginFormComponent.class);
    }
}