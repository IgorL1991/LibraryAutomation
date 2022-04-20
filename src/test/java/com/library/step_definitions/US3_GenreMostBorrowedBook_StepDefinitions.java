package com.library.step_definitions;

import com.library.utilities.MyDB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US3_GenreMostBorrowedBook_StepDefinitions {
    @When("I execute a query to find the most popular genre")
    public void i_execute_a_query_to_find_the_most_popular_genre() {
        MyDB_Util.runQuery("SELECT bc.name, COUNT(book_id) AS COUNT FROM book_borrow bb\n" +
                "INNER JOIN books b ON bb.book_id = b.id\n" +
                "INNER JOIN book_categories bc ON b.book_category_id = bc.id\n" +
                "GROUP BY bc.name\n" +
                "ORDER BY COUNT DESC");
    }
    @Then("Verify that {string} is the most popular book genre.")
    public void verify_that_is_the_most_popular_book_genre(String expectedResult) {
       String actualResult = MyDB_Util.getCellValue(1,1);
        System.out.println("expectedResult = " + expectedResult);
        System.out.println("actualResult = " + actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

}
