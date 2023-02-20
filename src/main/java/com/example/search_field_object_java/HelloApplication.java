package Lecture16.page.object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchField {
    private final WebDriver driver;

    public SearchField(WebDriver driver) {
        this.driver = driver;
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();
    }

    public void clickLogin() {
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
    }

    public void searchUser(String user) {
        WebElement searchUser = driver.findElement(By.id("search-bar"));
        searchUser.sendKeys(user);
    }

    public void addNewPost() {
        WebElement addNewPost = driver.findElement(By.id("nav-link-new-post"));
        addNewPost.click();
    }

    public void findAndClickUser(String user, String profileUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement findUser = driver.findElement(By.partialLinkText(user));
        wait.until(ExpectedConditions.visibilityOf(findUser));
        findUser.click();
        wait.until(ExpectedConditions.urlToBe(profileUrl));
    }
}

    public static void main(String[] args) {
        launch();
    }
}