## Projeto simples de automação

Neste projeto, execute a _**SuiteTeste**_, um teste automatizado simples, que preenche um formulário e valida os campos obrigatórios.

Exitem também outros testes como:  
 _**TesteAjax**_, que visa preencher um campo texto, clicar no botão de submit e aguardar o retorno.  
 _**TesteAlert**_, que visa a interagir com alert durante um teste automatizado.  
_**TesteFrameEJanelas**_, que visa a interação de um teste com automatizado em frames e alterando o foco entre janelas.  

Apresentado no durante o curso: https://www.udemy.com/course/testes-funcionais-com-selenium-webdriver/

#### :sparkles: Tecnologias
:heavy_check_mark: **Java**  
:heavy_check_mark: **JUnit**  
:heavy_check_mark: **Selenium WebDriver** com driver do **chrome** e **firefox** ja implementado.  

|-Por default o driver do chrome já esta setado.  
|-Porém de forma simples a alteração para o firefox.

**_Alterando driver_**  
No arquivo `src/main/java/br/rs/matheuspadilha/core/Propriedades.java`  
Altere a o valor do `Browsers.CHROME`, para o `Browsers.FIREFOX`  
Chrome: `public static Browsers browser = Browsers.CHROME;`  
Firefox: `public static Browsers browser = Browsers.FIREFOX;` 

#### :vertical_traffic_light: Requisitos 
Idea: **Eclipse** ou **IntelliJ**  
Versão: **Java 8**
