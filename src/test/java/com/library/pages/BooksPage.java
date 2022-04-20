package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BooksPage {
    public BooksPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchBar;

    @FindBy(xpath = "//tr[@class='odd']/td[3]")
    public WebElement bookName;

    @FindBy(xpath = "//tr[@class='odd']/td[4]")
    public WebElement bookAuthor;

    @FindBy(xpath = "//tr[@class='odd']/td[6]")
    public WebElement bookYear;

    @FindBy(xpath = "//tr[@class='odd']/td")
    public List<WebElement> bookInfoList;


    @FindBy(id = "book_categories")
    public WebElement bookCategories;
}
