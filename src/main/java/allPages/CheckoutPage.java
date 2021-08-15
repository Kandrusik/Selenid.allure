package allPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage extends BasePage {

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

    public CheckoutPage yourPersonInformation(String firstName, String lastName, String zipCode) {
        firstNameOrder.sendKeys(firstName);
        lastNameOrder.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    public void setCancelOrderButton() {
        cancelOrderButton.click();
    }

    public CheckoutPage setContinueOrderButton() {
        continueOrderButton.click();
        return this;
    }

    public CheckoutPage setFinishOrderButton() {
        finishOrderButton.click();
        return this;
    }

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