package com.library.step_definitions;
import com.library.pages.HomePage;
import com.library.pages.LoginPage;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import com.library.utilities.MyDB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2_BorrowedBooks_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    int expectedBorrowedBooks;
    @Given("I am in the homepage of the library app")
    public void i_am_in_the_homepage_of_the_library_app() {
        loginPage.loginAsLibrarian();
    }

    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {
        expectedBorrowedBooks = Integer.parseInt(homePage.borrowedBooks.getText());
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        MyDB_Util.runQuery("SELECT COUNT(id) FROM book_borrow WHERE is_returned = 0");
        int actualBorrowedBoos = Integer.parseInt(MyDB_Util.getCellValue(1,1));
        System.out.println("expectedBorrowedBooks = " + expectedBorrowedBooks);
        System.out.println("actualBorrowedBoos = " + actualBorrowedBoos);
        Assert.assertEquals(expectedBorrowedBooks,actualBorrowedBoos);
    }
}
