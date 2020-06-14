import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
    private String typeDriver = "webdriver.gecko.driver";
    private String fileDriver = "<caminho-driver>";
    private WebDriver driver;
    
    @Before
    public void inicializa() {
        System.setProperty(typeDriver, fileDriver);
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }
    
    @After
    public void finaliza() {
        driver.quit();
    }
    
    @Test
    public void teste() {
        driver.get("http://google.com");
        Assert.assertEquals("Google", driver.getTitle());
    }
}
