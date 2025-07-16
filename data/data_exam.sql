insert into subscription_type (quota_loans, max_loan_days, quota_reservations, quota_extends, penalty_days) values
    (2,7,1,3,10),
    (3,9,2,5,9),
    (4,12,3,7,8);


insert into member(last_name, first_name, birth_date, email, password, subscription_start_date, subscription_end_date, subscription_type_id, is_admin) values
    ('ETU001', 'Amine Bensaid', '1990-01-01', '1@g.c', '1', '2025-02-01', '2025-07-24', 1, false),
    ('ETU002', 'Sarah El', '1990-01-01', '2@g.c', '2', '2025-02-01', '2025-07-01', 1, false),
    ('ETU003', 'Youcef', '1990-01-01', '3@g.c', '3', '2025-04-01', '2025-12-01', 1, false),
    ('ENS001', 'Nadia', '1990-01-01', '4@g.c', '4', '2025-07-01', '2026-07-01', 1, false),
    ('ENS002', 'Karim', '1990-01-01', '5@g.c', '5', '2025-08-01', '2026-05-01', 1, false),
    ('ENS003', 'Salima', '1990-01-01', '6@g.c', '6', '2025-07-01', '2025-06-01', 1, false),
    ('PROF001', 'Rachid', '1990-01-01', '7@g.c', '7', '2025-06-01', '2025-12-01', 1, false),
    ('PROF002', 'Amina', '1990-01-01', '8@g.c', '8', '2024-10-01', '2025-06-01', 1, false);

insert into book (age_rating, author, title) values
    (1, 'Victor Hugo', 'Litterature classique'),
    (1, 'Albert Camus', 'Philosophie'),
    (1, 'Rowling', 'Jeunesse / Fantastique');

insert into book_copy (book_id) values
    (1),
    (1),
    (1),
    (2),
    (2),
    (3);

