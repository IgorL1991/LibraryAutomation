package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BasePage {
/**
 * In this class will store WebElements common for all pages
 */
@FindBy(id = "borrowed_books")
public WebElement borrowedBooks;

    @FindBy(xpath = "//ul[@id='menu_item']/li[3]")
    public List<WebElement> moduleList;
}
