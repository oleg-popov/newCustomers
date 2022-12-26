create table if not exists users(
id int primary key auto_increment,
name varchar(100) not null,
phone varchar(100) not null,
email varchar(100) not null,
login varchar(100) not null,
password varchar(100) not null,
status varchar(25) default 'ACTIVE'
);

create table if not exists roles(
id int primary key auto_increment,
name varchar(100) not null,
status varchar(25) default 'ACTIVE'
);

create table if not exists user_roles(
user_id int,
role_id int,
constraint fk_user_roles_user foreign key(user_id) references users(id),
constraint fk_user_roles_roles foreign key(role_id) references roles(id)
);

