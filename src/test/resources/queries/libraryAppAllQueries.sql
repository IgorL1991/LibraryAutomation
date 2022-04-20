-- US-1
SELECT id
FROM users;

-- US-2
SELECT COUNT(id)
FROM book_borrow
WHERE is_returned = 0;

-- US-3
SELECT bc.name, COUNT(book_id) AS COUNT
FROM book_borrow bb
         INNER JOIN books b ON bb.book_id = b.id
         INNER JOIN book_categories bc ON b.book_category_id = bc.id
GROUP BY bc.name
ORDER BY COUNT DESC;

-- US-4
SELECT full_name, count(*)
FROM users u
         INNER JOIN book_borrow bb ON u.id = bb.user_id
GROUP BY full_name
ORDER BY 2 DESC
LIMIT 1;

-- US-5
SELECT name, year, author
FROM books
WHERE name = 'Chordeiles minor';

-- US-6
SELECT name
FROM book_categories;
