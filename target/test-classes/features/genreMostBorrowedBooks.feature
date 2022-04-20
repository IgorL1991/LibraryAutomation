Feature: As a librarian, I want to know the genre of books are being borrowed the most

  @GenreMostBorrowedBooks
  Scenario: verify the common book genre that's being borrowed

    Given Establish the dataBase connection
    When I execute a query to find the most popular genre
    Then Verify that "Classic" is the most popular book genre.