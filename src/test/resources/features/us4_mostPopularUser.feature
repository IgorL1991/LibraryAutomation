Feature: As a librarian, O want to know who is the most popular user

  @MostPopularUser
  Scenario: Verify who is the most popular user who reads the most
    Given Establish the dataBase connection
    When I execute a query to find the most popular user
    Then Verify "Test Student 1" is the user who reads the most