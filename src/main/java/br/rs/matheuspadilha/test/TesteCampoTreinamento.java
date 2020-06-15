package br.rs.matheuspadilha.test;

import br.rs.matheuspadilha.core.DSL;
import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TesteCampoTreinamento {
    
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
    public void testeTextField() {
        dsl.preencherCampo("elementosForm:nome", "Teste de escrito");
        Assert.assertEquals("Teste de escrito", dsl.obterValorCampo("elementosForm:nome"));
    }
    
    @Test
    public void testeTextFieldDuplo() {
        dsl.preencherCampo("elementosForm:nome", "Texto 1");
        Assert.assertEquals("Texto 1", dsl.obterValorCampo("elementosForm:nome"));
        dsl.preencherCampo("elementosForm:nome", "Texto 2");
        Assert.assertEquals("Texto 2", dsl.obterValorCampo("elementosForm:nome"));
    }
    
    @Test
    public void deveInteragirComTextArea() {
        dsl.preencherCampo("elementosForm:sugestoes", "Teste");
        Assert.assertEquals("Teste", dsl.obterValorCampo("elementosForm:sugestoes"));
    }
    
    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }
    
    @Test
    public void deveInteragirComCheckbox() {
        dsl.clicarCheckbox("elementosForm:ComidaFavorita:2");
        Assert.assertTrue(dsl.isCheckMarcado("elementosForm:ComidaFavorita:2"));
    }
    
    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorSelecionadoCombo("elementosForm:escolaridade"));
    }
    
    @Test
    public void deveVerificarValoresCombo() {
        Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    }
    
    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        
        List<String > allSelectedOptions = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, allSelectedOptions.size());
        
        dsl.deselecionarCombo("elementosForm:esportes","Corrida");
        allSelectedOptions = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, allSelectedOptions.size());
        Assert.assertTrue(allSelectedOptions.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
    }
    
    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarButton("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
    }
    
    @Test
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }
    
    @Test
    public void deveBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
    }
    
    @Test
    public void testJavascript() {
        WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
        dsl.executarJS("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }
    
    @Test
    public void deveClicarBotaoTabela() {
        dsl.clicarBotaoTabela("Escolaridade", "Superior", "Botao","elementosForm:tableUsuarios");
    }
    
    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicarButton("buttonDelay");
        Thread.sleep(5000);
        dsl.preencherCampo("novoCampo", "deu certo?");
    }
    
    @Test
    public void deveUtilizarEsperaImplicita() {
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        dsl.clicarButton("buttonDelay");
        dsl.preencherCampo("novoCampo", "deu certo?");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    
    @Test
    public void deveUtilizarEsperaExplicita() {
        dsl.clicarButton("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.preencherCampo("novoCampo", "deu certo?");
    }
    
}
