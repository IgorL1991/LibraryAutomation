Feature: As a data consumer, I want UI and DB book information are match.

  Background:
    Given I am in the homepage of the library app

    @ChekUImatchWithDB
  Scenario: Verify book information with DB
    Given Establish the dataBase connection
    When I navigate to "Books" page
    And I open book "Chordeiles minor"
    Then book information must match the Database