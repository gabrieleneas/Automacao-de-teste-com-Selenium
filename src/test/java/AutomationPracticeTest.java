import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class AutomationPracticeTest {


    private String SITE_URL = "http://automationpractice.com/index.php";
    WebDriverWait wait;
    WebDriver driver;


    @BeforeEach
    public void acessarUrl(){
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.navigate().to(SITE_URL);
        wait = new WebDriverWait(driver, 10);
    }



    @Test
    public void criarConta(){
        acessarPaginaDeAutenticacao();
        driver.findElement(By.id("email_create")).sendKeys("gabreu@12341231234.com", Keys.ENTER);
        preencherInfoPessoal();
        preencherEndereco();
        submit();
        Assertions.assertTrue(driver.getPageSource().contains("Welcome to your account. Here you can manage all of your personal information and orders."));
    }



    @Test
    public void fazerLogin(){
        acessarPaginaDeAutenticacao();
        driver.findElement(By.id("email")).sendKeys("gabreu@1234.com");
        driver.findElement(By.id("passwd")).sendKeys("123456");
        driver.findElement(By.id("SubmitLogin")).click();
        Assertions.assertTrue(driver.getPageSource().contains("Welcome to your account. Here you can manage all of your personal information and orders."));

    }
    private void acessarPaginaDeAutenticacao() {
        driver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }
    private void preencherInfoPessoal() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1"))).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Mc");
        driver.findElement(By.id("customer_lastname")).sendKeys("Brinquedo");
        driver.findElement(By.id("passwd")).sendKeys("123456");

        new Select(driver.findElement(By.id("days"))).selectByValue("3");
        new Select(driver.findElement(By.id("months"))).selectByValue("8");
        new Select(driver.findElement(By.id("years"))).selectByValue("1980");

        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
    }

    private void preencherEndereco(){
        driver.findElement(By.id("firstname")).sendKeys("aaa");
        driver.findElement(By.id("lastname")).sendKeys("aaa");
        driver.findElement(By.id("company")).sendKeys("aaa");
        driver.findElement(By.id("address1")).sendKeys("Algum lugar");
        driver.findElement(By.id("city")).sendKeys("Alguma Cidade");

        new Select(driver.findElement(By.id("id_state"))).selectByValue("16");

        driver.findElement(By.id("postcode")).sendKeys("00000");
        driver.findElement(By.id("other")).sendKeys("selenium practice");
        driver.findElement(By.id("phone_mobile")).sendKeys("11111111");
        driver.findElement(By.id("alias")).sendKeys("bomdia");
    }

    private void submit(){
        driver.findElement(By.id("submitAccount")).click();
    }


}
