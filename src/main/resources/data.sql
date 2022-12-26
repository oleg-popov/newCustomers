insert into users(name, phone, email, login, password)
--login = admin | password = admin
values ('Oleg', '+380634852542', 'oleg@gmail.com', 'admin', '$2a$12$x146OFm5BAT5VU/yk9tWzuK2jJJPCkr3TBAPr59ZyUKYpyQpHiq7W'),
--login = dima | password = dima
       ('Dima', '+380507251305', 'dima@gmail.com', 'dima', '$2a$12$6TGQdkix2o7rVt7yyWMJLeFroNtqhNvc5B2d1UYfyrAeR04va3ejW');
insert into roles(name)values
('USER' ), ('ADMIN');
insert into user_roles (user_id , role_id)values
(1, 2) ,
(2, 1);