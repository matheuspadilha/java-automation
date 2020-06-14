import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {
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
    public void deveRealizarCadastroComSucesso() {
        dsl.preencherCampo("elementosForm:nome", "Matheus");
        dsl.preencherCampo("elementosForm:sobrenome", "Padilha");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:2");
        dsl.selecionarCombo("elementosForm:escolaridade","Superior");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
        dsl.clicarButton("elementosForm:cadastrar");
        
        Assert.assertTrue("Cadastrado!", dsl.obterTexto(By.id("resultado")).startsWith("Cadastrado!"));
        Assert.assertTrue("Matheus", dsl.obterTexto(By.id("descNome")).endsWith("Matheus"));
        Assert.assertEquals("Sobrenome: Padilha", dsl.obterTexto(By.id("descSobrenome")));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto(By.id("descSexo")));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto(By.id("descComida")));
        Assert.assertEquals("Escolaridade: superior", dsl.obterTexto(By.id("descEscolaridade")));
        Assert.assertEquals("Esportes: O que eh esporte?", dsl.obterTexto(By.id("descEsportes")));
    }
    
    @Test
    public void deveValidarNomeObrigatorio() {
        dsl.clicarButton("elementosForm:cadastrar");
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertObterTextoEAceita());
    }
    
    @Test
    public void deveValidarSobrenomeObrigatorio() {
        dsl.preencherCampo("elementosForm:nome", "Matheus");
        dsl.clicarButton("elementosForm:cadastrar");
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertObterTextoEAceita());
    }
    
    @Test
    public void deveValidarSexoObrigatorio() {
        dsl.preencherCampo("elementosForm:nome", "Matheus");
        dsl.preencherCampo("elementosForm:sobrenome", "Padilha");
    
        dsl.clicarButton("elementosForm:cadastrar");
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertObterTextoEAceita());
    }
    
    @Test
    public void deveValidarComidaVegetariana() {
        dsl.preencherCampo("elementosForm:nome", "Matheus");
        dsl.preencherCampo("elementosForm:sobrenome", "Padilha");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
        dsl.clicarButton("elementosForm:cadastrar");
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertObterTextoEAceita());
    }
    
    @Test
    public void deveValidarEsportistaIndeciso() {
        dsl.preencherCampo("elementosForm:nome", "Matheus");
        dsl.preencherCampo("elementosForm:sobrenome", "Padilha");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
        dsl.clicarButton("elementosForm:cadastrar");
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertObterTextoEAceita());
    }
}
