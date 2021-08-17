package allPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {

    // Message text
    public SelenideElement thankYouForYourOrder = $("#checkout_complete_container > h2");

    // Field Person
    SelenideElement firstNameOrder = $("#first-name");
    SelenideElement lastNameOrder = $("#last-name");
    SelenideElement zipCodeField = $("#postal-code");
    SelenideElement totalOrderMessage = $(".summary_subtotal_label");
    ElementsCollection inventoryItemPrice = $$(".inventory_item_price");

    // Other Button
    SelenideElement cancelOrderButton = $("#cancel");
    SelenideElement continueOrderButton = $("#continue");
    SelenideElement finishOrderButton = $("#finish");
    SelenideElement continueShoppingButton = $("#continue-shopping");

    @Step("Set checkout button")
    public CheckoutPage yourPersonInformation(String firstName, String lastName, String zipCode) {
        firstNameOrder.sendKeys(firstName);
        lastNameOrder.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    @Step("Set checkout button")
    public void setCancelOrderButton() {
        cancelOrderButton.click();
    }

    @Step("Set checkout button")
    public CheckoutPage setContinueOrderButton() {
        continueOrderButton.click();
        return this;
    }

    @Step("Set finish order button")
    public CheckoutPage setFinishOrderButton() {
        finishOrderButton.click();
        return this;
    }

    @Step("String price to double")
    public void stringPriceToDouble() {
        String stringTotalPrice = totalOrderMessage.getText().replace("Item total: $", "");
        Double doubleTotalPrice = Double.parseDouble(stringTotalPrice);
        String stringFirstInventoryItemPrice = inventoryItemPrice.first().getText().replace("$", "");
        String stringLastInventoryItemPrice = inventoryItemPrice.last().getText().replace("$", "");
        Double doubleFirstInventoryItemPrice = Double.parseDouble(stringFirstInventoryItemPrice);
        Double doubleLastInventoryItemPrice = Double.parseDouble(stringLastInventoryItemPrice);
        double doubleFirstAndLastPrice = doubleFirstInventoryItemPrice + doubleLastInventoryItemPrice;
        Assertions.assertEquals(doubleTotalPrice, doubleFirstAndLastPrice);
    }
}