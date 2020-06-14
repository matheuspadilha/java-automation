package br.rs.matheuspadilha.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static String typeDriver = "webdriver.gecko.driver";
    private static String fileDriver = "<caminho_driver>";
    private static WebDriver driver;
    
    private DriverFactory() {}
    
    public static WebDriver getDriver() {
        
        if (driver == null ){
            System.setProperty(typeDriver, fileDriver);
            driver = new FirefoxDriver();
            driver.manage().window().setSize(new Dimension(100, 400));
        }
        return driver;
    }
    
    public static void killDriver() {
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
    
}
