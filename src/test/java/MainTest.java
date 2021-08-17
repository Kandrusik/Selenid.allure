import allPages.BasePage;
import allPages.BasketPage;
import allPages.CheckoutPage;
import allPages.LoginPage;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest extends BasePage {

    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    BasketPage basketPage = new BasketPage();


    @Attachment(value = "Test attachment [{type}]", type = "text/plain", fileExtension = ".txt")
    public byte[] textAttachment(String type, String content) {
        return content.getBytes(StandardCharsets.UTF_8);
    }

    @BeforeEach
    public void setUp() throws IOException {
        Configuration.headless = true;
//        Configuration.startMaximized = true;
        loginPage.openLoginPage()
                .singIn()
                .welcomeMessage.shouldHave(text("PRODUCTS"));
        textAttachment("Annotated", "Здесь очень важная информация!");
    }


    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Owner(value = "Dmitry")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login test")
    @Issue("11")
    @Link(name = "standard_user", url = "https://www.saucedemo.com/")
    @Test
    @Order(1)
    public void testLoginTest() throws IOException {
        loginPage.openLoginPage()
                .singIn()
                .welcomeMessage.shouldHave(text("PRODUCTS"));
    }

    @Owner(value = "Dmitry")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test buy")
    @Issue("12")
    @Test
    @Order(2)
    public void testBuyThings() {
        basketPage.scanTableBody()
                .setAddAllItemToCartButton();
        basePage.setBasketButton();
        basketPage.setCheckoutButton();
        checkoutPage.yourPersonInformation("Oleg", "Oleg", "12345")
                .setContinueOrderButton()
                .setFinishOrderButton()
                .thankYouForYourOrder.shouldBe(text("THANK YOU FOR YOUR ORDER"));
    }


    @Severity(SeverityLevel.MINOR)
    @Owner(value = "Dmitry")
    @Description("Purchase cancellation test")
    @Issue("13")
    @Test
    @Order(3)
    public void testCancelOfBuyingThings() {
        basketPage.scanTableBody()
                .setAddAllItemToCartButton();
        basePage.setBasketButton();
        basketPage.setCheckoutButton();
        checkoutPage.yourPersonInformation("Oleg", "Oleg", "12345")
                .setContinueOrderButton()
                .setCancelOrderButton();
        loginPage.welcomeMessage.shouldHave(text("PRODUCTS"));
    }


    @Severity(SeverityLevel.NORMAL)
    @Owner(value = "Dmitry")
    @Description("Test for removing items from the cart")
    @Issue("14")
    @Test
    @Order(4)
    public void testDeletingItemsFromTheTrash() {
        basketPage.scanTableBody()
                .setAddAllItemToCartButton();
        basePage.setBasketButton();
        basketPage.setCancelAllButtonFromCart();
        basePage.setHomeButton()
                .shoppingCartBadge.shouldBe(hidden);
    }


    @Severity(SeverityLevel.TRIVIAL)
    @Owner(value = "Dmitry")
    @Description("Test to compare the expected price with the actual")
    @Issue("15")
    @Flaky
    @Test
    @Order(5)
    public void testCheckingTheTotalAmount() {
        basketPage.scanTableBody()
                .setAddAllItemToCartButton();
        basePage.setBasketButton();
        basketPage.setCheckoutButton();
        checkoutPage.yourPersonInformation("Oleg", "Oleg", "12345")
                .setContinueOrderButton()
                .stringPriceToDouble();
    }
}