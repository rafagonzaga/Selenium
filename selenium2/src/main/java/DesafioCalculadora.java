import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DesafioCalculadora {

    private static final String URL = "https://igorsmasc.github.io/calculadora_atividade_selenium/";

    private static WebDriver driver;

    @BeforeAll
    // Ocorre antes de tudo mesmo, antes do JUnit instanciar a classe de teste
    public static void setup() {
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(URL);
    }

    @Test
    void testaAdicao() {
        WebElement number1 = driver.findElement(By.id("five"));
        WebElement operation = driver.findElement(By.id("add"));
        WebElement number2 = driver.findElement(By.id("seven"));
        WebElement equals = driver.findElement(By.id("equals"));
        WebElement display = driver.findElement(By.className("form-control"));

        elementClick(number1);
        elementClick(operation);
        elementClick(number2);
        elementClick(equals);

        Assertions.assertEquals("12", display.getAttribute("value"));
    }

    @Test
    void testaSubtracao() {
        WebElement number1 = driver.findElement(By.id("nine"));
        WebElement operation = driver.findElement(By.id("subtract"));
        WebElement number2 = driver.findElement(By.id("two"));
        WebElement equals = driver.findElement(By.id("equals"));
        WebElement display = driver.findElement(By.xpath("//*[@id=\"display\"]"));

        number1.click();
        operation.click();
        number2.click();
        equals.click();

        Assertions.assertEquals("7", display.getAttribute("value"));
    }

    @Test
    void testaMultiplicacao() {
        WebElement number1 = driver.findElement(By.id("four"));
        WebElement operation = driver.findElement(By.id("multiply"));
        WebElement number2 = driver.findElement(By.id("five"));
        WebElement equals = driver.findElement(By.id("equals"));
        WebElement display = driver.findElement(By.xpath("//*[@id=\"display\"]"));

        number1.click();
        operation.click();
        number2.click();
        equals.click();

        Assertions.assertEquals("20", display.getAttribute("value"));
    }

    @Test
    void testaDivisaoPossivel() {
        WebElement number1 = driver.findElement(By.id("eight"));
        WebElement operation = driver.findElement(By.id("divide"));
        WebElement number2 = driver.findElement(By.id("two"));
        WebElement equals = driver.findElement(By.id("equals"));
        WebElement display = driver.findElement(By.xpath("//*[@id=\"display\"]"));

        number1.click();
        operation.click();
        number2.click();
        equals.click();

        Assertions.assertEquals("4", display.getAttribute("value"));
    }

    @Test
    void testaDivisaoImpossivel() {
        WebElement number1 = driver.findElement(By.id("six"));
        WebElement operation = driver.findElement(By.id("divide"));
        WebElement number2 = driver.findElement(By.id("zero"));
        WebElement equals = driver.findElement(By.id("equals"));
        WebElement display = driver.findElement(By.xpath("//*[@id=\"display\"]"));

        number1.click();
        operation.click();
        number2.click();
        equals.click();

        Assertions.assertEquals("Infinity", display.getAttribute("value"));
    }


    private void elementClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }


}
