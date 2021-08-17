package allPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    SelenideElement loginField = $("#user-name");
    SelenideElement passwordField = $("#password");
    public SelenideElement signInButton = $(By.name("login-button"));
    public SelenideElement welcomeMessage = $(By.xpath("//span[text()='Products']"));

    @Step("Sing in")
    public LoginPage singIn() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/user.properties"));
        loginField.sendKeys(props.getProperty("user.username"));
        passwordField.sendKeys(props.getProperty("user.password"));
        signInButton.click();
        return this;
    }

    @Step("Open login page")
    public LoginPage openLoginPage() {
        open("https://www.saucedemo.com/");
        return this;
    }
}