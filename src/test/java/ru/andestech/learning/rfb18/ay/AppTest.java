package ru.andestech.learning.rfb18.ay;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class AppTest
{
    private WebDriver driver;
    private Wait wait;

    @BeforeClass

    public void init()
    {
        // System.out.println( "++ driver starts..." );
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\and\\Downloads\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions co = new ChromeOptions();
        String profilePath = "C:\\Users\\and\\AppData\\Local\\Google\\Chrome\\User Data";


        co.addArguments("user-data-dir=" + profilePath);
        co.setExperimentalOption("useAutomationExtension", false);
        co.addArguments("--start-maximized");


        driver = new ChromeDriver(co);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,5);

        System.out.println( "++ driver started." + driver );
    }

    @Test
    @Ignore
    public void webTest2()
    {
        driver.get("http://localhost:8383/HTML5App3/index.html");

        List<WebElement> webList1 =
                driver.findElements(By.tagName("h1"));

        for(WebElement webElement: webList1)
        {
            System.out.println(webElement.getText());
        }

       WebElement webElement2 =
               driver.findElement(By.id("mainTitle"));

        System.out.println(webElement2.getCssValue("color"));
       assertEquals("Это тестовый сайт.", webElement2.getText());


        List<WebElement> webList2 =
                driver.findElements(By.cssSelector("ol>li.hg"));

        for(WebElement webElement: webList2)
        {
            System.out.println(webElement.getText());
        }
    }

    @Test
    public void webTest() {

        driver.get("http://localhost:8383/HTML5App3/index.html");

        driver.findElement(By.linkText("Новый пользователь")).click();
        wait.until(ExpectedConditions.titleIs("New customer"));

        WebElement webElementName =
                driver.findElement(By.id("name"));
        webElementName.clear();
        webElementName.sendKeys("Пётр");

        WebElement webElementSname =
                driver.findElement(By.id("sname"));
        webElementSname.clear();
        webElementSname.sendKeys("Пётров");

        System.out.println("name: " +webElementName.getAttribute("value") );
        System.out.println("sname: " +webElementSname.getAttribute("value") );

        WebElement web2= driver.findElement(By.id("group_selector"));
        Select select = new Select(web2);
        select.selectByValue("ooo");

        driver.findElement(By.id("login")).sendKeys("ppetrov" + new Random().nextInt(1000));
        driver.findElement(By.id("pass")).sendKeys("sS123456");

        driver.findElement(By.name("submit")).click();

    }


    @AfterClass
    public void free() throws InterruptedException {

        wait = null;
        if(driver != null) {
            TimeUnit.SECONDS.sleep(2);

            driver.close(); driver.quit();
            System.out.println("-- driver free ok.");}


    }
}
