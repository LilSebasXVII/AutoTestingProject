package SeleniumBasics;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/anton/Downloads/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setCapability("unhandledPromptBehavior", "accept"); // Accept all alerts automatically
        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // Maximize the window
    }

    @org.testng.annotations.Test
    public void test1() {
        driver.get("https://www.demoblaze.com/"); // Access page

        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        JavascriptExecutor js = (JavascriptExecutor) driver;  // Setup script
        js.executeScript("window.scrollBy(0, 200)"); // Scroll down 200 pixels

        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement Samsung = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")); // Find Samsung phone
        Samsung.click(); // Click phone

        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement Cart = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")); // Find cart
        Cart.click(); // Click cart

        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        // Get title and prints it
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();
        System.out.println("Current URL: " + currentUrl);
        System.out.println("Page Title: " + pageTitle);

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        Dimension windowSize = driver.manage().window().getSize(); // Get window size and print it
        System.out.println("Window Size: Width = " + windowSize.getWidth() + ", Height = " + windowSize.getHeight());

        List<WebElement> elements = driver.findElements(By.xpath("//*")); // Select all elements and print it
        System.out.println("Total number of elements on the page: " + elements.size());

        for (WebElement element : elements) {
            System.out.println("Tag Name: " + element.getTagName());
        }
    }

    @org.testng.annotations.Test
    public void test2() {
        driver.get("https://www.saucedemo.com/");

        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        // Locate username, password fields, and login button
        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        // Enter valid credentials
        usernameField.sendKeys("standard_user");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
        passwordField.sendKeys("secret_sauce");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
        loginButton.click();
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement menu = driver.findElement(By.id("react-burger-menu-btn"));
        menu.click(); // Open menu
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
        logout.click(); // Click logout
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        // Enter invalid credentials
        // Locate login elements again after logout
        usernameField = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        loginButton = driver.findElement(By.id("login-button"));

        // Enter invalid credentials
        usernameField.sendKeys("invalid_user");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
        passwordField.sendKeys("wrong_password");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
        loginButton.click();
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
    }

    @org.testng.annotations.Test
    public void test3() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement elements = driver.findElement(By.xpath("//*[@id=\"headingOne\"]/button"));
        elements.click(); // Click elements
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement links = driver.findElement(By.xpath("//*[@id=\"navMenus\"]/li[6]/a"));
        links.click(); // Click links
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        String originalWindow = driver.getWindowHandle(); // Get original window handle

        WebElement home = driver.findElement(By.xpath("/html/body/main/div/div/div[2]/p[1]/a"));
        home.click(); // Click home link
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle); // Switch to new window
                break;
            }
        }

        String currentURL = driver.getCurrentUrl(); // Get current page URL
        System.out.println("Current Page URL: " + currentURL);
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE); // Take screenshot

        // Define a valid file path (update with an existing directory)
        String destinationPath = ("C:\\Users\\anton\\Pictures\\Screenshots\\homescreenshot.png");

        // Ensure the path is valid
        File destination = new File(destinationPath);

        try {
            FileUtils.copyFile(source, destination); // Save screenshot to destination
            System.out.println("Screenshot saved at: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test
    public void test4() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement elements = driver.findElement(By.xpath("//*[@id=\"headingOne\"]/button"));
        elements.click(); // Click elements
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement uploaddownload = driver.findElement(By.xpath("//*[@id=\"navMenus\"]/li[8]/a"));
        uploaddownload.click(); // Click upload/download link
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement upload = driver.findElement(By.id("uploadFile"));
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
        upload.sendKeys("C:\\Users\\anton\\Pictures\\Screenshots\\homescreenshot.png"); // Upload file
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
    }

    @org.testng.annotations.Test
    public void test5() {
        driver.get("https://jqueryui.com/sortable/");
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement draggable = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[1]/ul/li[2]/a"));
        draggable.click(); // Click draggable
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']"))); // Switch to iframe
        WebElement itemToDrag = driver.findElement(By.xpath("//p[normalize-space()='Drag me to my target']"));
        WebElement itemTarget = driver.findElement(By.xpath("//p[normalize-space()='Drop here']"));
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
        Actions actions = new Actions(driver);
        actions.dragAndDrop(itemToDrag, itemTarget).build().perform(); // Perform drag and drop action
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        driver.switchTo().defaultContent(); // Switch back to default content

        WebElement resizable = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[1]/ul/li[3]/a"));
        resizable.click(); // Click resizable
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement frame = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
        driver.switchTo().frame(frame); // Switch to iframe for resizing
        WebElement resize = driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));

        Actions action = new Actions(driver);
        action.dragAndDropBy(resize, 300, 100).perform(); // Perform resize action
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        driver.switchTo().defaultContent(); // Switch back to default content
        WebElement selectable = driver.findElement(By.xpath("//*[@id=\"sidebar\"]/aside[1]/ul/li[4]/a"));
        selectable.click(); // Click selectable
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second

        WebElement frame2 = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
        driver.switchTo().frame(frame2); // Switch to iframe for selecting elements

        WebElement select = driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"));
        WebElement select2 = driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[2]"));
        WebElement select3 = driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"));
        WebElement select4 = driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[4]"));
        WebElement select5 = driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[5]"));

        select.click();
        select2.click();
        select3.click();
        select4.click();
        select5.click(); // Click all selectable items

        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait a second
    }

    @AfterTest
    public void tearDown() {
        driver.quit(); // Quit the browser
    }
}
