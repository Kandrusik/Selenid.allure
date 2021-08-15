package allPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage extends BasePage {

    // Field with products
    SelenideElement tableBody = $(".inventory_list");
    ElementsCollection cancelButtonFromCart = $$(".btn_small");
    ElementsCollection addItemToCartButton = $$(".btn_primary");
    SelenideElement checkoutButton = $("#checkout");

    public BasketPage scanTableBody() {
        tableBody.shouldBe(visible);
        return this;
    }

    public BasketPage setCancelAllButtonFromCart() {
        cancelButtonFromCart.first().click();
        cancelButtonFromCart.last().click();
        return this;
    }

    public BasketPage setAddAllItemToCartButton() {
        addItemToCartButton.first().click();
        addItemToCartButton.last().click();
        return this;
    }

    public BasketPage setCheckoutButton() {
        checkoutButton.click();
        return this;
    }
}
