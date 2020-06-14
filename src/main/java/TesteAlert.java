import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
    private String typeDriver = "webdriver.gecko.driver";
    private String driver = "<caminho-driver>";
    
    @Test
    public void deveInteragirComAlertSimples() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("Alert Simples", text);
        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(text);
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComAlertConfirm() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());
        alert.accept();
    
        driver.findElement(By.id("confirm")).click();
        alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();
        Assert.assertEquals("Negado", alert.getText());
        alert.accept();
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComAlertPrompt() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("7");
        alert.accept();
        Assert.assertEquals("Era 7?", alert.getText());
        alert.accept();
        Assert.assertEquals(":D", alert.getText());
        alert.accept();
        
        driver.quit();
    }
}
