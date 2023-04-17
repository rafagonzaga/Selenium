import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PraticandoLocatorsBotoes {

    private static final String URL = "https://igorsmasc.github.io/botoes_atividade_selenium/";

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
    void clicandoComID() {
        WebElement element = driver.findElement(By.id("button1"));
        element.click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("O botão 1 foi selecionado.", alert.getText());
    }

    @Test
    void clicandoComClassName() {
        WebElement element = driver.findElement(By.className("button-2"));
        element.click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("O botão 2 foi selecionado.", alert.getText());
    }

    @Test
    void clicandoComName() {
        WebElement element = driver.findElement(By.name("button3"));
        element.click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("O botão 3 foi selecionado.", alert.getText());
    }


    @Test
    void clicandoComCSS() {
        WebElement element = driver.findElement(By.cssSelector("#button4"));
        element.click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("O botão 4 foi selecionado.", alert.getText());
    }

    @Test
    void clicandoComXPath() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"button5\"]"));
        element.click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("O botão 5 foi selecionado.", alert.getText());
    }

    @Test
    void clicandoComLIVRE() {
        WebElement element = driver.findElement(By.id("button6-switch"));
        element.click();
//        WebElement element2 = driver.findElement(By.cssSelector("#button6"));
//        element2.click();

        driver.findElement(By.id("button6")).click();

        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("O botão 6 foi selecionado.", alert.getText());
    }

    @Test
    public void testeLinkCompleto() {
        WebElement link = driver.findElement(By.linkText("Link 1"));
        link.click();

        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("O link 1 foi selecionado.", alert.getText());
    }

    // Para teste negativo
//    @Test
//    public void testTryCatch() {
//        WebDriver driver2 = new FirefoxDriver();
//        WebDriverWait wait = new WebDriverWait(driver2, Duration.ofSeconds(10));
//
//        try {
//            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        } catch (TimeoutException e) {
//            System.out.println("O alerta não apareceu");
//        } finally {
//            driver.quit();
//        }
//    }




}
