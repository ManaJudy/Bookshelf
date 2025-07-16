INSERT INTO book (title, author, age_rating) VALUES ('Le Petit Prince', 'Antoine de Saint-Exupéry', 6);
INSERT INTO book (title, author, age_rating) VALUES ('1984', 'George Orwell', 16);
INSERT INTO book (title, author, age_rating) VALUES ('Harry Potter à l école des sorciers', 'J.K. Rowling', 10);
INSERT INTO book (title, author, age_rating) VALUES ('Les Misérables', 'Victor Hugo', 14);
INSERT INTO book (title, author, age_rating) VALUES ('L Étranger', 'Albert Camus', 15);
INSERT INTO book (title, author, age_rating) VALUES ('Le Seigneur des Anneaux', 'J.R.R. Tolkien', 12);
INSERT INTO book (title, author, age_rating) VALUES ('Don Quichotte', 'Miguel de Cervantès', 16);
INSERT INTO book (title, author, age_rating) VALUES ('Cendrillon', 'Charles Perrault', 4);
INSERT INTO book (title, author, age_rating) VALUES ('Le Comte de Monte-Cristo', 'Alexandre Dumas', 13);
INSERT INTO book (title, author, age_rating) VALUES ('La Peste', 'Albert Camus', 16);


-- 2 copies pour Le Petit Prince (book_id = 1)
INSERT INTO book_copy (book_id) VALUES (1);
INSERT INTO book_copy (book_id) VALUES (1);

-- 3 copies pour 1984 (book_id = 2)
INSERT INTO book_copy (book_id) VALUES (2);
INSERT INTO book_copy (book_id) VALUES (2);
INSERT INTO book_copy (book_id) VALUES (2);

-- 2 copies pour Harry Potter (book_id = 3)
INSERT INTO book_copy (book_id) VALUES (3);
INSERT INTO book_copy (book_id) VALUES (3);

-- 1 copie pour Les Misérables (book_id = 4)
INSERT INTO book_copy (book_id) VALUES (4);

-- 2 copies pour L'Étranger (book_id = 5)
INSERT INTO book_copy (book_id) VALUES (5);
INSERT INTO book_copy (book_id) VALUES (5);

-- 2 copies pour Le Seigneur des Anneaux (book_id = 6)
INSERT INTO book_copy (book_id) VALUES (6);
INSERT INTO book_copy (book_id) VALUES (6);

-- 1 copie pour Don Quichotte (book_id = 7)
INSERT INTO book_copy (book_id) VALUES (7);

-- 2 copies pour Cendrillon (book_id = 8)
INSERT INTO book_copy (book_id) VALUES (8);
INSERT INTO book_copy (book_id) VALUES (8);

-- 2 copies pour Le Comte de Monte-Cristo (book_id = 9)
INSERT INTO book_copy (book_id) VALUES (9);
INSERT INTO book_copy (book_id) VALUES (9);

-- 1 copie pour La Peste (book_id = 10)
INSERT INTO book_copy (book_id) VALUES (10);
