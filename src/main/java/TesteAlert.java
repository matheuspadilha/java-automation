import br.rs.matheuspadilha.core.DSL;
import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteAlert {
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
