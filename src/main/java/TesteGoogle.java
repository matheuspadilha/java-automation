import static br.rs.matheuspadilha.core.DriverFactory.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteGoogle {
    
    @Before
    public void inicializa() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }
    
    @After
    public void finaliza() {
        killDriver();
    }
    
    @Test
    public void teste() {
        getDriver().get("http://google.com");
        Assert.assertEquals("Google", getDriver().getTitle());
    }
    
}
