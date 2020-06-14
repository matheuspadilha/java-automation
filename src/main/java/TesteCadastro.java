import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
    private String typeDriver = "webdriver.gecko.driver";
    private String driver = "<caminho-driver>";
    
    @Test
    public void deveRealizarCadastroComSucesso() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Padilha");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        
        Assert.assertEquals("Cadastrado!", driver.findElement(By.id("resultado")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Matheus", driver.findElement(By.id("descNome")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Padilha", driver.findElement(By.id("descSobrenome")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Masculino", driver.findElement(By.id("descSexo")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("Pizza", driver.findElement(By.id("descComida")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("superior", driver.findElement(By.id("descEscolaridade")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("O que eh esporte?", driver.findElement(By.id("descEsportes")).findElement(By.tagName("span")).getText());
        Assert.assertEquals("", driver.findElement(By.id("descSugestoes")).findElement(By.tagName("span")).getText());
       
        driver.quit();
    }
    
    @Test
    public void deveValidarNomeObrigatorio() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio",  alert.getText());
        alert.accept();
        
        driver.quit();
    }
    
    @Test
    public void deveValidarSobrenomeObrigatorio() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio",  alert.getText());
        alert.accept();
    
        driver.quit();
    }
    
    @Test
    public void deveValidarSexoObrigatorio() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Padilha");
        
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio",  alert.getText());
        alert.accept();
    
        driver.quit();
    }
    
    @Test
    public void deveValidarComidaVegetariana() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Padilha");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?",  alert.getText());
        alert.accept();
        
        driver.quit();
    }
    
    @Test
    public void deveValidarEsportistaIndeciso() {
        System.setProperty(this.typeDriver, this.driver);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Padilha");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
    
        Select sports = new Select(driver.findElement(By.id("elementosForm:esportes")));
        sports.selectByVisibleText("Corrida");
        sports.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?",  alert.getText());
        alert.accept();
        
        driver.quit();
    }
}
