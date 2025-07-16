insert into subscription_type (name, quota_loans, max_loan_days, quota_reservations, quota_extends, penalty_days) values
    ('etudiant', 2,7,1,3,10),
    ('enseignant', 3,9,2,5,9),
    ('profesionnel', 4,12,3,7,8);


insert into member(first_name, last_name, birth_date, email, password, subscription_start_date, subscription_end_date, subscription_type_id, is_admin) values
    ('Admin', 'admin', '1990-01-01', 'admin@example.com', 'admin', '2000-01-01', '3000-01-01', 3, true),
    ('ETU001', 'Amine Bensaid', '1990-01-01', 'ETU001@example.com', 'ETU001', '2025-02-01', '2025-07-24', 1, false),
    ('ETU002', 'Sarah El Khattabi', '1990-01-01', 'ETU002@example.com', 'ETU002', '2025-02-01', '2025-07-01', 1, false),
    ('ETU003', 'Youcef Moujahid', '1990-01-01', 'ETU003@example.com', 'ETU003', '2025-04-01', '2025-12-01', 1, false),
    ('ENS001', 'Nadia Benali', '1990-01-01', 'ENS001@example.com', 'ENS001', '2025-07-01', '2026-07-01', 2, false),
    ('ENS002', 'Karim Haddadi', '1990-01-01', 'ENS002@example.com', 'ENS002', '2025-08-01', '2026-05-01', 2, false),
    ('ENS003', 'Salima Touhami', '1990-01-01', 'ENS003@example.com', 'ENS003', '2025-07-01', '2025-06-01', 2, false),
    ('PROF001', 'Rachid El Mansouri', '1990-01-01', 'PROF001@example.com', 'PROF001', '2025-06-01', '2025-12-01', 3, false),
    ('PROF002', 'Amina Zerouali', '1990-01-01', 'PROF002@example.com', 'PROF002', '2024-10-01', '2025-06-01', 3, false);

insert into book (age_rating, author, title) values
    (1, 'Victor Hugo', 'Les Miserables'),
    (1, 'Albert Camus', 'L etranger'),
    (1, 'Rowling', 'Harry Potter a l ecole des sorciers');

insert into book_copy (book_id) values
    (1),
    (1),
    (1),
    (2),
    (2),
    (3);

insert into holiday (local_date) values
    ('2025-07-13'),
    ('2025-07-20'),
    ('2025-07-27'),
    ('2025-08-03'),
    ('2025-08-10'),
    ('2025-08-17'),
    ('2025-07-26'),
    ('2025-07-19');

insert into loan_type (max_loan_days, name) values
    (7, 'Standard'),
    (14, 'Long-term'),
    (3, 'Short-term');