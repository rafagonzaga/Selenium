import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PraticandoAlertas {

    private static final String URL = "https://igorsmasc.github.io/alertas_atividade_selenium/";

    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new FirefoxDriver();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(URL);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void alertaSimples() {
        WebElement botaoAlertaSimples = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
        botaoAlertaSimples.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("Este é um alerta simples!", alert.getText());
        alert.accept();
//        alert.dismiss(); // Funciona como o accept. Ele libera o alerta.
    }

    @Test
    public void alertaConfirmacaoAceitar() {
        WebElement botaoAlertaConfirmacao = driver.findElement(By.xpath("/html/body/div[1]/button[2]"));
        botaoAlertaConfirmacao.click();

        Alert alertConfirmacao = driver.switchTo().alert();

        assertEquals("Tem certeza que deseja excluir este item?", alertConfirmacao.getText());
        alertConfirmacao.accept();
//        alert.dismiss(); // Funciona como o accept. Ele libera o alerta.
        assertEquals("Item excluído com sucesso!", alertConfirmacao.getText());
    }

    @Test
    public void alertaConfirmacaoCancelar() {
        WebElement botaoAlertaConfirmacao = driver.findElement(By.xpath("/html/body/div[1]/button[2]"));
        botaoAlertaConfirmacao.click();

        Alert alertConfirmacao = driver.switchTo().alert();

        assertEquals("Tem certeza que deseja excluir este item?", alertConfirmacao.getText());
//        alertConfirmacao.accept();
        alertConfirmacao.dismiss(); // Funciona como o accept. Ele libera o alerta.
        assertEquals("Item excluído com sucesso!", alertConfirmacao.getText());
    }

    @Test
    public void alertaEntrada() {
        WebElement botaoAlertaConfirmacao = driver.findElement(By.xpath("/html/body/div[1]/button[3]"));
        botaoAlertaConfirmacao.click();

        Alert alertConfirmacao = driver.switchTo().alert();

        String nome = "Rafael";
        alertConfirmacao.sendKeys(nome);
        alertConfirmacao.accept();
//        alert.dismiss(); // Funciona como o accept. Ele libera o alerta.
        assertEquals("Bem-vindo, Rafael!", alertConfirmacao.getText());
    }

    @Test
    public void naoFazNada() {
        assertFalse(existeAlertaAberto());
    }

    @Test
    public void alertaNaoDisparado() {
//        WebElement botaoAlertaSimples = driver.findElement(By.xpath("/html/body/div[1]/button[1]"));
//        botaoAlertaSimples.click();

        try {
            Alert alert = driver.switchTo().alert();
            fail();
        } catch (NoAlertPresentException e) {
//            assertEquals("no such alert", e.getMessage());
        }

//        assertEquals("Este é um alerta simples!", alert.getText());
//        alert.accept();
//        alert.dismiss(); // Funciona como o accept. Ele libera o alerta.
    }

    private boolean existeAlertaAberto() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



    private void elementClick(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
    }


}
