package com.aepd.test;

import com.codeborne.selenide.Configuration;
import com.aepd.page.Sede.comun.AftSedeTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseTest {

    public WebDriver driver;
    private String PATH_SCREENSHOTS = null;
    private final String NAME_FILE_ERROR_STARTS_WITH = "SCREEN_ERROR";

   @BeforeMethod
    public void setup(Method testMethod) throws IOException {

        //Borramos el directorio para que esté limpio la siguiente ejecución del test
        FileUtils.deleteDirectory(new File(AftSedeTest.downloadsFolderPath));
        ChromeOptions options = new ChromeOptions();
        Configuration.browserSize = "1920x1080";
        Configuration.downloadsFolder = AftSedeTest.downloadsFolderPath;
        options.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;
        open("https://sede-cer-vdc-1.agpd.local/sede-electronica-web/");
        getWebDriver().manage().timeouts().pageLoadTimeout(80, SECONDS);
        if ($(byText("Accept all")).isDisplayed()) {
            $(byText("Accept all")).shouldBe(visible).click();
            $(byText("Accept all")).should(disappear);
        }
    }

    /**
     * Close Chrome
     */
    @AfterMethod
    public void teardown() throws IOException {
        getWebDriver().quit();
    }
}