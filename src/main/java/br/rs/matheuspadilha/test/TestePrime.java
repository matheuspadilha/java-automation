package br.rs.matheuspadilha.test;

import br.rs.matheuspadilha.core.DSL;
import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestePrime {
    
    private DSL dsl;
    
    @Before
    public void inicializa() {
        dsl = new DSL();
    }
    
    @After
    public void finaliza() {
        killDriver();
    }
    
    @Test
    public void deveInteragirComRadioPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt726:console:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:0"));
        dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:1"));
    }
    
    @Test
    public void deveInteragirComSelectPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl.selecionarComboPrime("j_idt726:console", "PS4");
        Assert.assertEquals("PS4", dsl.obterTexto("j_idt726:console_label"));
    }
    
}
