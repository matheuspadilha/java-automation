package br.rs.matheuspadilha.page;

import br.rs.matheuspadilha.core.BasePage;
import org.openqa.selenium.By;

public class CampoTreinamentoPage extends BasePage {
    
    public void setNome(String nome) {
        dsl.preencherCampo("elementosForm:nome", nome);
    }
    
    public void setSobrenome(String sobrenome) {
        dsl.preencherCampo("elementosForm:sobrenome", sobrenome);
    }
    
    public void setSexoMasculino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }
    
    public void setSexoFeminino() {
        dsl.clicarRadio("elementosForm:sexo:1");
    }
    
    public void setComidaCarne() {
        dsl.clicarCheckbox("elementosForm:comidaFavorita:0");
    }
    
    public void setComidaFrango() {
        dsl.clicarCheckbox("elementosForm:comidaFavorita:1");
    }
    
    public void setComidaPizza() {
        dsl.clicarCheckbox("elementosForm:comidaFavorita:2");
    }
    
    public void setComidaVegetariano() {
        dsl.clicarCheckbox("elementosForm:comidaFavorita:3");
    }
    
    public void setEscolaridade(String value) {
        dsl.selecionarCombo("elementosForm:escolaridade", value);
    }
    
    public void setEsporte(String... values) {
        for (String value: values) {
            dsl.selecionarCombo("elementosForm:esportes", value);
        }
    }
    
    public void cadastrar() {
        dsl.clicarButton("elementosForm:cadastrar");
    }
    
    public String obterResultadoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
    }
    
    public String obterNomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
    }
    
    public String obterSobrenomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
    }
    
    public String obterSexoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
    }
    
    public String obterComidaCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
    }
    
    public String obterEscolaridadeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
    }
    
    public String obterEsporteCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
    }
    
}
