package com.library.pages;

import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputEmail")
    public WebElement userInput;

    @FindBy(id = "inputPassword")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signInButton;

    public void loginAsLibrarian() {
        String username = ConfigurationReader.getProperty("librarianUser");
        String password = ConfigurationReader.getProperty("librarianPassword");
        userInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
    }

    public void loginAsStudent() {
        String username = ConfigurationReader.getProperty("studentUser");
        String password = ConfigurationReader.getProperty("studentPassword");
        userInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
    }
}
