package br.rs.matheuspadilha.suites;

import br.rs.matheuspadilha.core.DriverFactory;
import br.rs.matheuspadilha.test.TesteCadastro;
import br.rs.matheuspadilha.test.TesteRegrasCadastro;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegrasCadastro.class
})
public class SuiteTeste {
    
    @AfterClass
    public static void finalizaTudo() {
        DriverFactory.killDriver();
    }
}
