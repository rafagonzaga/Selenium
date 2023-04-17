import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectTest {

    private static final String URL = "https://igorsmasc.github.io/praticando_selects_radio_checkbox/";

    private static WebDriver driver;

    @BeforeAll
    // Ocorre antes de tudo mesmo, antes do JUnit instanciar a classe de teste
    public static void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
//        driver.get(URL);
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(URL);
    }

//    @AfterAll
//    public static void tearDown() {
//        driver.quit();
//    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

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
}
