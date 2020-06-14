import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
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
    public void deveInteragirComFrames() {
        dsl.entrarFrame("frame1");
        dsl.clicarButton("frameButton");
        String msg = dsl.alertObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
        dsl.sairFrame();
        dsl.preencherCampo("elementosForm:nome", msg);
    }
    
    @Test
    public void deveInteragirComJanelas() {
        dsl.clicarButton("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.preencherCampo(By.tagName("textarea"),"Deu certo?");
        driver.close();
        dsl.trocarJanela("");
        dsl.preencherCampo(By.tagName("textarea"),"e agora?");
    }
    
    @Test
    public void deveInteragirComJanelasSemTitulo() {
        dsl.clicarButton("buttonPopUpHard");
        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
        dsl.preencherCampo(By.tagName("textarea"),"Deu certo?");
        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
        dsl.preencherCampo(By.tagName("textarea"),"e agora?");
    }
}
