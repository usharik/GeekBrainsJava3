-- создаем базу данных  
create database network_chat;

-- выбираем созданную базу как активную
use network_chat;

-- выводим список таблиц
show tables;

-- создаем таблицу пользователей
create table if not exists users (
	id int auto_increment primary key,
    login varchar(25),
    password varchar(25),
    unique index uq_login(login)
);

-- выбрать всех пользователей из таблицы
select * from users;

-- выбрать пользователя с логином ivan
select * from users where login = 'ivan';

-- добавить двух пользователей в таблицу
insert into users(login, password)
values ('ivan', '123'), 
			('petr', '345');
            
-- удалить пользователя из таблицы
drop table users;