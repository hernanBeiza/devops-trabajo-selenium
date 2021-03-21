package cl.hiperactivo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthUI;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest 
{
    private static WebDriver driver = new ChromeDriver();

    @BeforeClass
    public static void setUp(){
        System.out.println("Iniciando configuración para la prueba...");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        //driver = new ChromeDriver();
        //driver.get("https://www.amazon.com");
        //driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1200, 900));
        driver.manage().window().setPosition(new Point(200, 100));
        //System.out.println("URL: " + driver.getCurrentUrl());
        //System.out.println("Title: " + driver.getTitle());
        //driver.navigate().to("https://www.google.com");
    }

    @Test
    public void test1EntrarSitio()
    {
        System.out.println("Iniciando testEntrarSitio()");
        driver.navigate().to("http://automationpractice.com/index.php");
        assertNotNull(driver.getTitle());
    }

    @Test
    public void test2Buscar()
    {
        System.out.println("Iniciando testBuscar()");

        WebElement cajaBusqueda = driver.findElement(By.id("search_query_top"));
        cajaBusqueda.sendKeys("blouse");
        cajaBusqueda.submit();
        assertNotNull(cajaBusqueda);
    }

    @Test
    public void test3EscogerProducto()
    {
        System.out.println("Iniciando testEscogerProducto()");
        WebDriverWait wait = new WebDriverWait(driver, 4);
        WebElement divProductos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-container")));
        //WebElement divProductos = driver.findElement(By.className("product-container"));
        List<WebElement> productos = divProductos.findElements(By.className("product_img_link"));
        System.out.println(productos);
        productos.get(0).click();
        assertNotNull(productos.get(0));
    }

    @Test
    public void test4AgregarAlCarro()
    {
        System.out.println("Iniciando testAgregarAlCarro()");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fancybox-type-iframe")));
        driver.switchTo().frame("product");
        System.out.println(modal);
        modal.click();
        WebElement boton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("exclusive")));
        System.out.println(boton);
        //Cambiar al modal que contiene el webElement encontrado
        //driver.switchTo().activeElement();
        assertNotNull(modal);
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("tearDown");
        //driver.close();
    }

}
