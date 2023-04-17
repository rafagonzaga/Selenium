import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PraticandoLocators {

    private static final String URL = "https://igorsmasc.github.io/praticando_locators_selenium/";

    private static WebDriver driver;

    @BeforeAll
    // Ocorre antes de tudo mesmo, antes do JUnit instanciar a classe de teste
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void locatorByID() {
//        Assertions.assertEquals("", driver.getTitle());

        WebElement element = driver.findElement(By.id("locator-id"));

        Assertions.assertEquals("Achei com ID", element.getText());

    }

    @Test
    public void locatorByClassName() {
        WebElement element = driver.findElement(By.className("locator-class-name"));
        Assertions.assertEquals("Achei com Class Name", element.getText());
    }

    @Test
    public void locatorByName() {
        WebElement element = driver.findElement(By.name("locator-name"));
        Assertions.assertEquals("Achei com Name", element.getText());
    }

    @Test
    public void locatorByCSSSelector() {
//        WebElement element = driver.findElement(By.cssSelector("#css-selector"));
//        WebElement element = driver.findElement(By.cssSelector(".css-selector-locator"));
//        WebElement element = driver.findElement(By.cssSelector(".lead.css-selector-locator"));
        WebElement element = driver.findElement(By.cssSelector("div p.lead.css-selector-locator")); // ter um
        // espaço antes do ponto faz toda a diferença
        Assertions.assertEquals("Achei com CSS Selector", element.getText());

    }

    @Test
    public void locatorByLinkText() {
        WebElement element = driver.findElement(By.linkText("Achei com o link text"));
        Assertions.assertEquals("Achei com o link text", element.getText());
    }

    @Test
    public void locatorByPartialLinkText() {
        WebElement element = driver.findElement(By.partialLinkText("Achei com o partial"));

        Assertions.assertEquals("https://github.com/", element.getAttribute("href"));
    }

    @Test
    public void locatorByTagName() {
        WebElement element = driver.findElement(By.tagName("details"));
        Assertions.assertEquals("Achei com Tag Name", element.getText());
    }

    @Test
    public void locatorByXPath() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"locator-xpath\"]"));
        Assertions.assertEquals("Achei com o XPATH", element.getText());
    }

}
