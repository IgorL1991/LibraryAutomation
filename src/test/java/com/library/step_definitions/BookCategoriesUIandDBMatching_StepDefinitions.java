package com.library.step_definitions;

import com.library.pages.BooksPage;
import com.library.utilities.MyDB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BookCategoriesUIandDBMatching_StepDefinitions {
    // creating an object of booksPage class
    BooksPage booksPage = new BooksPage();
    // declaring an empty List
    List<String> expectedCategories = new ArrayList<>();
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {

        //Creating an object of Select class and put the locator inside
        Select selectList = new Select(booksPage.bookCategories);
        //Getting all the categories from list and put in another List
        selectList.getOptions().forEach(p->expectedCategories.add(p.getText()));
        //Removing first element from List because it's name is "ALL"
        expectedCategories.remove(0);

    }
    @When("I execute a query to get book categories")
    public void i_execute_a_query_to_get_book_categories() {

        //creating a query to get all the Categories from DB
        MyDB_Util.runQuery("SELECT name FROM book_categories");

    }
    @Then("verify book categories must match the book_categories table from DB.")
    public void verify_book_categories_must_match_the_book_categories_table_from_db() {

        // collecting all the Categories from DB in List of Strings
        List<String>actualCategories =  MyDB_Util.getColumnDataAsList(1);

        // printing both objects
        System.out.println("expectedCategories = " + expectedCategories);
        System.out.println("actualCategories = " + actualCategories);

        //Checking if expecting result from UI matching with actual result from DB
        Assert.assertEquals(expectedCategories,actualCategories);

    }

}
