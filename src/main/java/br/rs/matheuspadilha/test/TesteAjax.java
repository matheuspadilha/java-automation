package br.rs.matheuspadilha.test;

import br.rs.matheuspadilha.core.DSL;
import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {
    
    private DSL dsl;
    
    @Before
    public void inicializa() {
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL();
    }
    
    @After
    public void finaliza() {
        killDriver();
    }
    
    @Test
    public void deveInteragirComRadioPrime() {
        dsl.preencherCampo("j_idt725:name", "Teste Ajax");
        dsl.clicarButton("j_idt725:j_idt728");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt799_start")));
        Assert.assertEquals("Teste Ajax", dsl.obterTexto("j_idt725:display"));
    }
    
}


