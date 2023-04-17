import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeiraAutomacao {

    private static final String URL = "https://www.google.com/";

    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @Test
    public void test_google_chrome() {
//        WebDriverManager.chromedriver().setup();    // Configura o driver
//        WebDriver driver = new ChromeDriver();      // Instancia o ChromeDriver que fará uso do driver baixado

        driver.get(URL);

        assertEquals("Google", driver.getTitle());

        assertEquals("https://www.google.com/", driver.getCurrentUrl());
//        System.out.println(driver.getPageSource());
    }

    @Test
    public void test_google_firefox() {
//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();

        driver.get(URL);

        assertEquals("Google", driver.getTitle());
    }

    @Test
    public void test_google_edge() {
//        WebDriverManager.edgedriver().setup();
//        WebDriver driver = new EdgeDriver();

        driver.get(URL);

        assertEquals("Google", driver.getTitle());
    }

    @Test
    public void test_google_janela() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get(URL);

        driver.manage().window().setPosition(new Point(100, 100));
        driver.manage().window().minimize();
        driver.manage().window().maximize();
//        driver.quit();  // Encerra os chromedrivers dos processos do windows
    }

}
