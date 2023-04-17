import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DesafioSelect {

    private static final String URL = "https://rachacuca.com.br/logica/problemas/show-de-talentos/";

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
    public void selectTest() {
        WebElement element = driver.findElement(By.id("categoria"));
        Select selectCategoria = new Select(element);

        selectCategoria.selectByIndex(1);

        assertEquals("Almoço e Janta", selectCategoria.getFirstSelectedOption().getText());
    }

    @Test
    public void selectMultiploTest() {
        WebElement element = driver.findElement(By.id("ingredientes"));
        Select selectIngredientes = new Select(element);

        selectIngredientes.selectByVisibleText("Ovos");
        selectIngredientes.selectByVisibleText("Frango");

        List<WebElement> elementosSelecionados = selectIngredientes.getAllSelectedOptions();

        List<String> elementosSelecionadosString =
                elementosSelecionados.stream().map(elemento -> elemento.getText()).collect(Collectors.toList());

        assertArrayEquals(new String[]{"Ovos", "Frango"} ,elementosSelecionadosString.toArray());
    }

    @Test
    public void checkBoxTest() {
        WebElement checkForno = driver.findElement(By.id("forno"));
        WebElement checkAirFryer = driver.findElement(By.id("air-fryer"));
        WebElement checkGeladeira = driver.findElement(By.id("geladeira"));
        WebElement checkMicroondas = driver.findElement(By.id("microondas"));

        checkForno.click();
        checkAirFryer.click();

        assertTrue(checkForno.isSelected());
        assertTrue(checkAirFryer.isSelected());

        assertFalse(checkGeladeira.isSelected());
        assertFalse(checkMicroondas.isSelected());
    }

    @Test
    public void radioButtonTest() {
        WebElement facil = driver.findElement(By.id("facil"));
        WebElement medio = driver.findElement(By.id("medio"));

        elementClick(medio);
        assertTrue(medio.isSelected());
        assertFalse(facil.isSelected());

        elementClick(facil);
        assertTrue(facil.isSelected());
        assertFalse(medio.isSelected());
    }

    private void elementClick(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
    }


}
