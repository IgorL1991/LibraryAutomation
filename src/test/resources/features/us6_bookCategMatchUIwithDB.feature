Feature: As a data consumer, I want UI and DB book categories match.

  Background:
    Given I am in the homepage of the library app

    @BookCategories
  Scenario: verify book categories with DB
    Given Establish the dataBase connection
    When I navigate to "Books" page
    And I take all book categories in UI
    And I execute a query to get book categories
    Then verify book categories must match the book_categories table from DB.