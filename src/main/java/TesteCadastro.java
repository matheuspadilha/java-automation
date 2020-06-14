import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteCadastro {
    private CampoTreinamentoPage page;
    
    @Before
    public void inicializa() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page = new CampoTreinamentoPage();
    }
    
    @After
    public void finaliza() {
        killDriver();
    }
    
    @Test
    public void deveRealizarCadastroComSucesso() {
        page.setNome("Matheus");
        page.setSobrenome("Padilha");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Superior");
        page.setEsporte("O que eh esporte?");
        page.cadastrar();
        
        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
        Assert.assertEquals("Matheus", page.obterNomeCadastro());
        Assert.assertEquals("Padilha", page.obterSobrenomeCadastro());
        Assert.assertEquals("Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Pizza", page.obterComidaCadastro());
        Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
        Assert.assertEquals("O que eh esporte?", page.obterEsporteCadastro());
    }
    
}
