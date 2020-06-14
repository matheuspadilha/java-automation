import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
    private String typeDriver = "webdriver.gecko.driver";
    private String driver = "<caminho-driver>";
    
    @Test
    public void deveInteragirComFrames() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();
    
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
     
        driver.quit();
    }
    
    @Test
    public void deveInteragirComJanelas() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();
        
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
    
        driver.quit();
    }
    
    @Test
    public void deveInteragirComJanelasSemTitulo() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("buttonPopUpHard")).click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
        
        driver.quit();
    }
}
