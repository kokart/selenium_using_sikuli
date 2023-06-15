package com.aepd.page.Sede.ConsultaDPD;


import com.aepd.page.BasePage;
import com.codeborne.selenide.Configuration;
import com.aepd.page.Sede.comun.AftSedeTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ConsultaAEPDPage extends BasePage {



    public ConsultaAEPDPage(WebDriver driver) {
        super(driver);
    }

    public void openConsultasAEPD() throws IOException {
        //Borramos el directorio para que esté limpio la siguiente ejecución del test
        FileUtils.deleteDirectory(new File(AftSedeTest.downloadsFolderPath));
        Configuration.downloadsFolder = AftSedeTest.downloadsFolderPath;
        Configuration.reportsFolder = "target/reports";
        ChromeOptions options = new ChromeOptions();
        Configuration.browserSize = "1920x1080";
        options.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;
        open(URL_CONSULTAS_AEPD);
        getWebDriver().manage().timeouts().pageLoadTimeout(80, SECONDS);
    }

    public void clickConfirmarDatos() {
        confirmacionDatosClickEnviar();
        $(By.xpath("//*[@id='tinycontent']/div/input[1]")).click();
        sleep(1000);
        Alert alert = switchTo().alert();
        alert.accept();
        sleep(1000);
        //Botón acceder de clave usando certificado electrónico
        $(By.xpath("//*[@id='wrap']/div[2]/section/div/ul/li[1]/article/div[2]/div/fieldset/div/a/span")).click();
    }

    private static void confirmacionDatosClickEnviar() {
        $(By.id("botonFirmar")).click();
    }

    public void rellenarFormularioConsultaConClasificacionTituloConsulta(String codigo, String titulo, String consulta) {
        formularioConsultaCorreoElectronico();
        formularioConsultaIntroducirConsulta(codigo, titulo, consulta);
        sleep(1000);
        formularioConsultaClickEnviarDatos();
        sleep(3000);
    }

    private static void formularioConsultaClickEnviarDatos() {
        $(By.id("formularioConsulta:enviarDatos")).click();
    }

    private static void formularioConsultaIntroducirConsulta(String codigo, String titulo, String consulta) {
        $(By.id("formularioConsulta:clasificacion")).selectOptionByValue(codigo);
        $(By.id("formularioConsulta:tituloConsulta")).val(titulo);
        $(By.id("formularioConsulta:consultaDesc")).val(consulta);
    }

    private static void formularioConsultaCorreoElectronico() {
        $(By.id("formularioConsulta:correoElectronico")).val(AftSedeTest.ConDPDCorreoElectronico);
    }

    public void clickConfirmarDatosCP() {
        $(By.id("botonFirmar")).click();
        $(By.xpath("//*[@id='tinycontent']/div/input[1]")).click();
        sleep(1000);
        Alert alert = switchTo().alert();
        alert.accept();
        sleep(1000);
    }
    public void clickObtenerJustificante() {
        //sleep(35000);
        $(By.id("formDescargaPDF:botonJustificante")).shouldBe(visible, Duration.ofSeconds(40));
        $(By.id("formDescargaPDF:botonJustificante")).click();
        sleep(1000);
    }
}