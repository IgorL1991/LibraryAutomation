package com.library.step_definitions;

import com.library.pages.BooksPage;
import com.library.pages.HomePage;
import com.library.utilities.MyDB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class US5_BookInfoUIandDB_StepDefinitions {
    HomePage homePage = new HomePage();
    BooksPage booksPage = new BooksPage();
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String books) {
        for (WebElement each : homePage.moduleList) {
            if(each.getText().equals(books)){
                each.click();
            }
        }
    }
    @And("I open book {string}")
    public void i_open_book(String bookName) {
        booksPage.searchBar.sendKeys(Keys.ENTER,bookName);
        MyDB_Util.runQuery("SELECT name, year, author\n" +
                "FROM books\n" +
                "WHERE name = 'Chordeiles minor'");
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        List<String> expectedBookInfo = new ArrayList<>();
        List<String> actualBookInfo = MyDB_Util.getRowDataAsList(1);
        booksPage.bookInfoList.forEach(p->expectedBookInfo.add(p.getText()));

        System.out.println("expectedBookInfo = " + expectedBookInfo);
        System.out.println("actualBookInfo = " + actualBookInfo);

        Assert.assertTrue(expectedBookInfo.containsAll(actualBookInfo));

    }

}
