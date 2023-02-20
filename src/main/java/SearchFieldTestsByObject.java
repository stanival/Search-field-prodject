public class SearchFieldTestsByObject {
}
 package Lecture16;

         import Lecture16.page.factory.*;
         import io.github.bonigarcia.wdm.WebDriverManager;
         import org.openqa.selenium.WebDriver;
         import org.openqa.selenium.chrome.ChromeDriver;
         import org.testng.Assert;
         import org.testng.annotations.*;

         import java.time.Duration;

public class SearchFieldTestsByFactory {
    private WebDriver driver;

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
    }

    @AfterMethod
    protected final void tearDownTest() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }


    @DataProvider(name = "searchUsers")
    public Object[][] searchUsers() {
        return new Object[][]{
                {"slavi", "http://training.skillo-bg.com:4300/users/3755"}
        };
    }

    @Test(dataProvider = "searchUsers")
    public void testSearchFieldOnProfilePage(String user, String profileUrl) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
        loginPage.populateUsername("wild_man");
        loginPage.populatePassword("storko99");
        loginPage.clickSignIn();

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, "wild_man", "The username is incorrect!");

        SearchField searchField = new SearchField(driver);
        searchField.searchUser(user);
        searchField.findAndClickUser(user, profileUrl);

        Assert.assertEquals(profilePage.getUsername(), user);
    }

    @Test(dataProvider = "searchUsers")
    public void testSearchFieldOnNewPostPage(String user, String profileUrl) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
        loginPage.populateUsername("wild_man");
        loginPage.populatePassword("storko99");
        loginPage.clickSignIn();

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        header.clickNewPost();
        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded(), "The New post URL is not correct!");

        String titleOnNewPostPage = newPostPage.getTitleText();
        Assert.assertEquals(titleOnNewPostPage, "Post a picture to share with your awesome followers", "The title is incorrect.");

        SearchField searchField = new SearchField(driver);
        searchField.searchUser(user);
        searchField.findAndClickUser(user, profileUrl);

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertEquals(profilePage.getUsername(), user);
    }

    @Test(dataProvider = "searchUsers")
    public void testSearchFieldOnHomePage(String user, String profileUrl) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
        loginPage.populateUsername("wild_man");
        loginPage.populatePassword("storko99");
        loginPage.clickSignIn();

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        SearchField searchField = new SearchField(driver);
        searchField.searchUser(user);
        searchField.findAndClickUser(user, profileUrl);

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertEquals(profilePage.getUsername(), user);
    }
}