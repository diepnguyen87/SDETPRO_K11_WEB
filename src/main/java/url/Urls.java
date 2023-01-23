package url;

public interface Urls {

    //String demoBaseURL = "https://demowebshop.tricentis.com";
    String demoBaseURL = System.getProperty("baseURL");

    String baseUrl = "https://the-internet.herokuapp.com";
    String dropdownSlug = "/dropdown";
    String iframeSlug = "/iframe";
    String hoverSlug = "/hovers";
    String jScriptSlug = "/javascript_alerts";
    String loginSlug = "/login";
    String dynamicControlSlug = "/dynamic_controls";
    String floatingMenuSlug = "/floating_menu";
}
