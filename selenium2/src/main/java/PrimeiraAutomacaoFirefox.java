import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PrimeiraAutomacaoFirefox {

    @Test
    public void test_google() {
        WebDriverManager.firefoxdriver().setup();    // Configura o driver
        WebDriver driver = new FirefoxDriver();      // Instancia o ChromeDriver que fará uso do driver baixado

        driver.get("https://www.google.com");

        Assertions.assertEquals("Google", driver.getTitle());
    }
}
