Feature: As a librarian, I want to know the amount of borrowed books

  Background: Connecting to DB
    Given Establish the dataBase connection

  @BorrowedBooks
  Scenario: verify the amount of borrowed books
    Given I am in the homepage of the library app
    When I take borrowed books number
    Then borrowed books number information must match with DB