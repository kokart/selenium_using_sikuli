package com.aepd.test.ConsultasAEPD;

import com.aepd.page.BasePage;
import com.aepd.page.Sede.ConsultaDPD.ConsultaAEPDPage;
import com.aepd.test.BaseTest;
import com.codeborne.selenide.testng.ScreenShooter;
import org.openqa.selenium.By;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Listeners({ ScreenShooter.class})
public class sikuliTest extends BaseTest {
    ConsultaAEPDPage consulta = new ConsultaAEPDPage(driver);

    @Test(description = "Click botón aceptar")
    public void aceptarAutenticacionCertificado() throws IOException, AWTException, FindFailed, InterruptedException {
        consulta.abrirURL(BasePage.URL_CONSULTAS_AEPD);
        consulta.clickLinkPorTexto("Electrónico");
        //Click en certificado dentro de clave
        $(By.xpath("//*[@id='wrap']/div[2]/section/div/ul/li[1]/article/div[2]/div/fieldset/div/a/span")).click();

        //Creamos pantalla, le pasamos el patrón de la imagen que tiene que buscar(aceptar.png), y si la encuentra, hace click.
        Screen screen = new Screen();
        Pattern pattern = new Pattern("C:\\aceptar.png");
        screen.click(pattern);
        //Fin codigo Sikuli
        consulta.clickLinkPorTexto("Acceso al trámite");

    }


}
