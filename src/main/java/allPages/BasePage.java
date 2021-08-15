package allPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    public SelenideElement shoppingCartBadge = $(By.id(".shopping_cart_badge"));
    SelenideElement basketButton = $("#shopping_cart_container > a");
    SelenideElement sighOutButton = $(By.id("logout_sidebar_link"));
    SelenideElement menuButton = $("#react-burger-menu-btn");
    SelenideElement homeButton = $("#inventory_sidebar_link");
    SelenideElement shoppingCartLink = $(By.id(".shopping_cart_link"));

    public void setBasketButton() {
        basketButton.click();
    }

    public BasePage setHomeButton() {
        menuButton.click();
        homeButton.click();
        return this;
    }
}