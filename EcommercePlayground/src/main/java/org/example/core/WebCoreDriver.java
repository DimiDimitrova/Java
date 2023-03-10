package org.example.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.enums.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebCoreDriver extends Driver {
    private ThreadLocal<WebDriver> webDriver;
    private ThreadLocal<WebDriverWait> webDriverWait;

    @Override
    public void start(Browser browser) {
        webDriver = new ThreadLocal<>();
        webDriverWait = new ThreadLocal<>();
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                webDriver.set(new ChromeDriver());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver.set(new FirefoxDriver());
                break;
            default:
                throw new IllegalArgumentException(browser.name());
        }

        webDriverWait.set(new WebDriverWait(webDriver.get(), Duration.ofSeconds(30)));
        webDriver.get().manage().window().maximize();
    }

    @Override
    public void quit() {
        webDriver.get().quit();
    }

    @Override
    public void goToUrl(String url) {
        webDriver.get().navigate().to(url);
    }

    @Override
    public Element findElement(By locator) {

        WebElement webElement =
            webDriverWait.get().until(ExpectedConditions.presenceOfElementLocated(locator));

        return new WebCoreElement(webDriver.get(), webElement, locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        var nativeWebElements =
            webDriverWait.get().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        var elements = new ArrayList<Element>();
        for (var nativeWebElement : nativeWebElements) {
            Element element = new WebCoreElement(webDriver.get(), nativeWebElement, locator);
            elements.add(element);
        }
        return elements;
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.get().getCurrentUrl();
    }

    @Override
    public void waitForAjax() {
        var javascriptExecutor = (JavascriptExecutor) webDriver.get();
        webDriverWait.get().until(d -> javascriptExecutor.executeScript("return window.jQuery != undefined && jQuery.active == 0"));
    }

    public void waitUntilPageLoadsCompletely() {
        var js = (JavascriptExecutor) webDriver.get();
        webDriverWait.get().until(d -> {
            return js.executeScript("return document.readyState").toString().equals("complete");
        });
    }

    @Override
    public void scrollToVisible(By element) {
        try {
            ((JavascriptExecutor) webDriver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
