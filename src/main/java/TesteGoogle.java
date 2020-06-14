import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
    private String typeDriver = "webdriver.gecko.driver";
    private String driver = "<caminho-driver>";
    
    @Test
    public void teste() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        
        driver.manage().window().setSize(new Dimension(100, 400));
        
        driver.get("http://google.com");
        Assert.assertEquals("Google", driver.getTitle());
        
        driver.quit();
    }
}
