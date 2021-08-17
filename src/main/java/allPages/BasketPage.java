package allPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage {

    // Field with products
    SelenideElement tableBody = $(".inventory_list");
    ElementsCollection cancelButtonFromCart = $$(".btn_small");
    ElementsCollection addItemToCartButton = $$(".btn_primary");
    SelenideElement checkoutButton = $("#checkout");

    @Step("Scan table body")
    public BasketPage scanTableBody() {
        tableBody.shouldBe(visible);
        return this;
    }

    @Step("Set cancel all button from cart")
    public BasketPage setCancelAllButtonFromCart() {
        cancelButtonFromCart.first().click();
        cancelButtonFromCart.last().click();
        return this;
    }

    @Step("Set add all item to cart button")
    public BasketPage setAddAllItemToCartButton() {
        addItemToCartButton.first().click();
        addItemToCartButton.last().click();
        return this;
    }

    @Step("Set checkout button")
    public BasketPage setCheckoutButton() {
        checkoutButton.click();
        return this;
    }
}
