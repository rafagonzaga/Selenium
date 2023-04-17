import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestSelectCheckRadio {

    private static final String URL = "https://igorsmasc.github.io/praticando_selects_radio_checkbox/";

    private static WebDriver driver;

    @BeforeAll
    // Ocorre antes de tudo mesmo, antes do JUnit instanciar a classe de teste
    public static void setup() {
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless=new");
        driver = new FirefoxDriver();
//        driver.get(URL);
//        driver.manage().window().maximize();
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
//        medio.click();
        assertTrue(medio.isSelected());
        assertFalse(facil.isSelected());

        elementClick(facil);
//        facil.click();
        assertTrue(facil.isSelected());
        assertFalse(medio.isSelected());
    }

    private void elementClick(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);
    }


//    @AfterEach
//    public void tearDown() {
//        driver.quit(); // Fecha tudo. o .close() fecha apenas uma janela
//    }


    /*
    @Test
    public void testSelect() {
        WebElement selectWebElement = driver.findElement(By.id("categoria"));
        Select select = new Select(selectWebElement);

//        select.selectByValue("lanche");
//        select.selectByIndex(2);
        select.selectByVisibleText("Almoço e Janta");

        Assertions.assertEquals("Almoço e Janta", select.getFirstSelectedOption().getText());
//        Assertions.assertTrue(select.getFirstSelectedOption().isSelected());
    }

    @Test
    public void testSelectMultiplo() {
        WebElement selectMultiploWebElement = driver.findElement(By.id("ingredientes"));
        Select select = new Select(selectMultiploWebElement);

        Assertions.assertEquals(7, select.getOptions().size());
    }

    @Test
    public void testSelectMultiploValidaValorDasOptions() {
        WebElement selectMultiploWebElement = driver.findElement(By.id("ingredientes"));
        Select select = new Select(selectMultiploWebElement);
        System.out.println(select.getOptions().stream().map(e -> e.getText()).collect(Collectors.toList()));


        List<String> listaRetornada = select.getOptions().stream().map(e -> e.getText()).collect(Collectors.toList());
        List<String> listaEsperada = Arrays.asList("Ovos", "Chocolate", "Frango", "Manteiga", "Leite", "Farinha", "A" +
                "çucar");

        Assertions.assertArrayEquals(listaEsperada.toArray(), listaRetornada.toArray());
    }

    @Test
    public void testSelectMultiploValidaSelecionadosValorDasOptions() {
        WebElement selectMultiploWebElement = driver.findElement(By.id("ingredientes"));
        Select select = new Select(selectMultiploWebElement);

        select.selectByVisibleText("Ovos");
        select.selectByVisibleText("Chocolate");

        List<String> ingredientesText =
                select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> ingredientesEsperados = Arrays.asList("Ovos", "Chocolate");

        Assertions.assertEquals(2, select.getAllSelectedOptions().size());
        Assertions.assertArrayEquals(ingredientesText.toArray(),
                ingredientesEsperados.toArray());
    }

     */
}
