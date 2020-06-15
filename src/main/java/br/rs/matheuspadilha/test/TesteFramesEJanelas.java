package br.rs.matheuspadilha.test;

import br.rs.matheuspadilha.core.DSL;
import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TesteFramesEJanelas {
    
    private DSL dsl;
    
    @Before
    public void inicializa() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }
    
    @After
    public void finaliza() {
        killDriver();
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
    public void deveInteragirComFrameEscondido(){
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.entrarFrame("frame2");
        dsl.clicarButton("frameButton");
        String msg = dsl.alertObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
    }
    
    @Test
    public void deveInteragirComJanelas() {
        dsl.clicarButton("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.preencherCampo(By.tagName("textarea"),"Deu certo?");
        getDriver().close();
        dsl.trocarJanela("");
        dsl.preencherCampo(By.tagName("textarea"),"e agora?");
    }
    
    @Test
    public void deveInteragirComJanelasSemTitulo() {
        dsl.clicarButton("buttonPopUpHard");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
        dsl.preencherCampo(By.tagName("textarea"),"Deu certo?");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
        dsl.preencherCampo(By.tagName("textarea"),"e agora?");
    }
    
}
