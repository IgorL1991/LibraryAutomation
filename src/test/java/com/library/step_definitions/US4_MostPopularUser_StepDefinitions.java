package com.library.step_definitions;

import com.library.utilities.MyDB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US4_MostPopularUser_StepDefinitions {
    @When("I execute a query to find the most popular user")
    public void i_execute_a_query_to_find_the_most_popular_user() {
        MyDB_Util.runQuery("SELECT full_name, count(*)\n" +
                "FROM users u\n" +
                "    INNER JOIN book_borrow bb ON u.id = bb.user_id\n" +
                "GROUP BY full_name\n" +
                "ORDER BY 2 DESC LIMIT 1");
    }

    @Then("Verify {string} is the user who reads the most")
    public void verify_is_the_user_who_reads_the_most(String expectedName) {
        String actualName = MyDB_Util.getCellValue(1,1);
        System.out.println("expectedName = " + expectedName);
        System.out.println("actualName = " + actualName);
        Assert.assertEquals(expectedName,actualName);
    }

}
