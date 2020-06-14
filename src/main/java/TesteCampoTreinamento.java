import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;

public class TesteCampoTreinamento {
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
}
