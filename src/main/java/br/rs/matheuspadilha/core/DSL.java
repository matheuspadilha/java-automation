package br.rs.matheuspadilha.core;

import static br.rs.matheuspadilha.core.DriverFactory.getDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class DSL {
     
    /********* TextField e TextArea ************/
    public void preencherCampo(By by, String text) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(text);
    }
    
    public void preencherCampo(String id, String text) {
        preencherCampo(By.id(id), text);
    }
    
    public String obterValorCampo(String id) {
        return getDriver().findElement(By.id(id)).getAttribute("value");
    }
    
    /********* Radio e Check ************/
    public void clicarRadio(By by) {
        getDriver().findElement(by).click();
    }
    
    public void clicarRadio(String id) {
        clicarRadio(By.id(id));
    }
    
    public boolean isRadioMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }
    
    public void clicarCheckbox(String id) {
        getDriver().findElement(By.id(id)).click();
    }
    
    public boolean isCheckMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }
    
    /********* Combo ************/
    public void selecionarCombo(String id, String value) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(value);
    }
    
    public void deselecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }
    
    public String obterValorSelecionadoCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }
    
    public int obterQuantidadeOpcoesCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }
    
    public boolean verificarOpcaoCombo(String id, String value) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        boolean encontrou = false;
        for ( WebElement option: options){
            if (option.getText().equals(value)){
                encontrou = true;
                break;
            }
        }
       return encontrou;
    }
    
    public List<String> obterValoresCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();
        for(WebElement opcao: allSelectedOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }
    
    public void selecionarComboPrime(String radical, String valor) {
        clicarRadio(By.xpath("//*[@id='" + radical + "_input']/../..//span"));
        clicarRadio(By.xpath("//*[@id='" + radical + "_items']//li[.='" + valor + "']"));
    }
    
    /********* Botao ************/
    public void clicarButton(String id) {
        getDriver().findElement(By.id(id)).click();
    }
    
    public String obterValueElemento(String id) {
        return getDriver().findElement(By.id(id)).getAttribute("value");
    }
    
    /********* Link ************/
    public void clicarLink(String link) {
        getDriver().findElement(By.linkText(link)).click();
    }
    
    /********* Textos ************/
    public String obterTexto(By by) {
        return getDriver().findElement(by).getText();
    }
    
    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }
    
    /********* Alerts ************/
    public String alertObterTexto(){
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }
    
    public String alertObterTextoEAceita(){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;
        
    }
    
    public String alertObterTextoENega(){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;
        
    }
    
    public void alertEscrever(String valor) {
        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys(valor);
        alert.accept();
    }
    
    /********* Frames e Janelas ************/
    public void entrarFrame(String id) {
        getDriver().switchTo().frame(id);
    }
    
    public void sairFrame(){
        getDriver().switchTo().defaultContent();
    }
    
    public void trocarJanela(String id) {
        getDriver().switchTo().window(id);
    }
    
    /********* JS ************/
    public Object executarJS(String command, Object... params){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(command, params);
    }
    
    /********* Tabela ************/
    public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
        // procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='" + idTabela + "']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);
        
        // procurar linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);
        
        // procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
        
        // clicar no botao da celula encontrada
        WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
        celula.findElement(By.xpath(".//input")).click();
    }
    
    protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
        int idLinha = - 1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(valor)){
                idLinha = i + 1;
                break;
            }
        }
        return idLinha;
    }
    
    protected int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equals(coluna)){
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }
    
}
