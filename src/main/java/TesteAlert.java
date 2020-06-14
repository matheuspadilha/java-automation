import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
    private String typeDriver = "webdriver.gecko.driver";
    private String fileDriver = "<caminho-driver>";
    private WebDriver driver;
    private DSL dsl;
    
    @Before
    public void inicializa() {
        System.setProperty(typeDriver, fileDriver);
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(100, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }
    
    @After
    public void finaliza() {
        driver.quit();
    }
    
    @Test
    public void deveInteragirComAlertSimples() {
        dsl.clicarButton("alert");
        String text = dsl.alertObterTextoEAceita();
        Assert.assertEquals("Alert Simples", text);
        dsl.preencherCampo("elementosForm:nome", text);
    }
    
    @Test
    public void deveInteragirComAlertConfirm() {
        dsl.clicarButton("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertObterTextoEAceita());
        Assert.assertEquals("Confirmado", dsl.alertObterTextoEAceita());
    
        dsl.clicarButton("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertObterTextoENega());
        Assert.assertEquals("Negado", dsl.alertObterTextoEAceita());
    }
    
    @Test
    public void deveInteragirComAlertPrompt() {
        dsl.clicarButton("prompt");
        Assert.assertEquals("Digite um numero", dsl.alertObterTexto());
        dsl.alertEscrever("7");
        Assert.assertEquals("Era 7?", dsl.alertObterTextoEAceita());
        Assert.assertEquals(":D", dsl.alertObterTextoEAceita());
    }
}
