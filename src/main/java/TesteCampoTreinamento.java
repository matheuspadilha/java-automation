import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {
    private String typeDriver = "webdriver.gecko.driver";
    private String driver = "<caminho-driver>";
    
    @Test
    public void testeTextField() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrito");
        Assert.assertEquals("Teste de escrito", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComTextArea() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
        Assert.assertEquals("Teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    
        driver.quit();
    }
    
    @Test
    public void deveInteragirComRadioButton() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComCheckbox() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:ComidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:ComidaFavorita:2")).isSelected());
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComCombo() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        combo.selectByVisibleText("2o grau completo");
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
        
        driver.quit();
    }
    
    @Test
    public void deveVerificarValoresCombo() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        
        Assert.assertEquals(8, options.size());
        
        boolean encontrou = false;
        for ( WebElement option: options){
            if (option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        
        Assert.assertTrue(encontrou);
        
        driver.quit();
    }
    
    @Test
    public void deveVerificarValoresComboMultiplo() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());
        
        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComBotoes() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        WebElement button = driver.findElement(By.id("buttonSimple"));
        button.click();
        Assert.assertEquals("Obrigado!", button.getAttribute("value"));
        
        driver.quit();
    }
    
    @Test
    public void deveInteragirComLinks() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        WebElement button = driver.findElement(By.linkText("Voltar"));
        button.click();
        Assert.assertEquals("Voltou!",  driver.findElement(By.id("resultado")).getText());
        
        driver.quit();
    }
    
    @Test
    public void deveBuscarTextosNaPagina() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",  driver.findElement(By.className("facilAchar")).getText());
        
        driver.quit();
    }
}
