package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public interface Frequencia {

    static void registrarFrequencia(String cpf, String senha) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\bot\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        navegador.get("https://pontoeletronico.goias.gov.br/sfr-frequenciaWeb/view/registrarFrequencia.xhtml");
        navegador.findElement(By.name("registrarFrequenciaForm:txCpf")).sendKeys(cpf);
        navegador.findElement(By.name("registrarFrequenciaForm:txSenha")).sendKeys(senha);
        navegador.findElement(By.id("registrarFrequenciaForm:btnUpdateCapth")).click();
        TimeUnit.SECONDS.sleep(3);
        String text = navegador.findElement(By.id("registrarFrequenciaForm:divMessages")).getText();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(text.toUpperCase());
        new SMS(text);
    }


}
