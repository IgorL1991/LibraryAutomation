package com.library.step_definitions;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.MyDB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.sql.SQLException;
import java.util.List;

public class VerifyStoringInfoDB_StepDefinitions {
    String url = ConfigurationReader.getProperty("url");
    String username = ConfigurationReader.getProperty("username");
    String password = ConfigurationReader.getProperty("password");

    @Given("Establish the dataBase connection")
    public void establish_the_data_base_connection() {
        MyDB_Util.connectionDB(url,username,password);
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users(){
        MyDB_Util.runQuery("SELECT id FROM users");
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        List<String> id_List = MyDB_Util.getColumnDataAsList(1);
        int count = 0;
        for (int i = 0; i <id_List.size(); i++) {
            for (int j = i+1; j <id_List.size(); j++) {
                if(id_List.get(i) == id_List.get(j)){
                    count++;
                }
            }
        }
        Assert.assertTrue(count == 0);
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        MyDB_Util.runQuery("SELECT * FROM users");
    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedColumnNames) {
        List<String> actualColumnNames = MyDB_Util.getAllColumnNamesAsList();
        Assert.assertEquals(expectedColumnNames,actualColumnNames);
    }
}


